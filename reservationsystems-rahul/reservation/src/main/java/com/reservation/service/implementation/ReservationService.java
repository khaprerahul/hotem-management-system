package com.reservation.service.implementation;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.reservation.entity.ReservationEntity;
import com.reservation.exception.ReservationEntityNotFoundException;
import com.reservation.mapper.IMapper;
import com.reservation.model.IGuest;
import com.reservation.model.IReservation;
import com.reservation.model.implementation.ReservationStatus;
import com.reservation.proxy.IGuestInformationProxy;
import com.reservation.proxy.IHotelInformationProxy;
import com.reservation.proxy.IPaymentServiceProxy;
import com.reservation.proxy.model.hotel.IHotel;

import com.reservation.repository.implementation.ReservationRepository;
import com.reservation.response.ApiResponseImpl;
import com.reservation.service.IPaymentService;
import com.reservation.service.IReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Reservation service implementation.
 * Responsible for handling actual business logic.
 */
public class ReservationService implements IReservationService {

    @Autowired
    private IGuestInformationProxy guestProxy;

    @Autowired
    private IHotelInformationProxy hotelProxy;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private IPaymentServiceProxy paymentServiceProxy;

    @Inject
    private IMapper mapper;

    @Autowired
    private IPaymentService iPaymentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

    /**
     * Responsible to call external Guest service, fetch information and convert response in
     * appropriate format understandable by Reservation service.
     * @param guestId , Retrieves information based on provided input.
     * @return IGuest, Holds information related to Guest.
     */
    public com.reservation.proxy.model.guest.IGuest getGuestById(Long guestId){
        ApiResponseImpl<com.reservation.proxy.model.guest.IGuest> guestResponseEntity = guestProxy.getGuest(guestId);
        return guestResponseEntity.getActualResponse();
    }

    /**
     * Responsible to call external Hotel service, fetch information and convert response in
     * appropriate format understandable by Reservation service.
     * @param hotelId , Retrieve hotel information based on provided input.
     * @return IHotel, Holds hotel information.
     */
    public IHotel getHotelById(Long hotelId){
        return hotelProxy.getHotelById(hotelId).getActualResponse();
    }

    /**
     * Responsible to create new reservation request of guest.
     * Creates new entry in Reservation Database, also call Hotel service to notify.
     * @param reservation , Reservation information like, duration of stay, guestID,
     *                    hotelId, kind of room, etc
     * @return IReservation, Stored reservation information.
     */
    public IReservation requestForReservation(IReservation reservation) {
        LOGGER.debug("ReservationService :: requestForReservation :: Request for new reservation {}", reservation);
        ReservationEntity newReservation = reservationRepository.save(mapper.mapIReservationToReservationDTO(reservation));
        hotelProxy.reservationRequest(mapper.mapReservationDTOToIReservation(newReservation), newReservation.getHotelId());
        return mapper.mapReservationDTOToIReservation(newReservation);
    }

    /**
     * Responsible to made changes in existing reservation. Changes like Confirm, cancel
     * reservation. It also responsible to call external services bases on change request.
     * If request is to CONFIRM reservation,
     *      1. calls Hotel service to confirm,
     *      2. calls Guest service to store reservation of history purpose and card information,
     *      3. finally calls payment service to make payment for reservation.
     * If request id to CANCEL reservation,
     *      1. calls Hotel service to update reservation,
     *      2. Calls payment service to credit payment towards guest.
     * @param reservation, reservation information to update.
     * @return IReservation, updated reservation information.
     * @throws ReservationEntityNotFoundException
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public IReservation updateReservation(IReservation reservation) throws ReservationEntityNotFoundException {
        LOGGER.debug("ReservationService :: updateReservation :: Request to update reservation {} ", reservation);
        ReservationEntity reservationEntity = reservationRepository.getReservationById(reservation.getReservationId());
        if (reservationEntity != null) {
            reservationEntity.setState(reservation.getState().toString());
            hotelProxy.updateReservation(reservation.getHotelId(), reservation);
            if (reservation.getState().equals(ReservationStatus.CONFIRM)) {
                guestProxy.addStayByGuest(reservation.getGuestId(), reservation.getReservationId());
                reservationEntity.setCard(mapper.mapICardToCardDTO(reservation.getCard()));
                guestProxy.addNewCard(reservationEntity.getGuestId(), reservation.getCard());
                 iPaymentService.doPayment(reservation.getCard(), reservation.getAmount(), reservation.getReservationId());
            }
            else if(reservation.getState().equals(ReservationStatus.CANCELLED)){
                paymentServiceProxy.revertPayment(mapper.mapICardToProxy(mapper.mapCardDTOToICard(reservationEntity.getCard())), reservationEntity.getAmount());
            }

            return mapper.mapReservationDTOToIReservation(reservationEntity);
        }
        else
        {
            throw new ReservationEntityNotFoundException("Reservation Entity with ID "+reservation.getReservationId()+" not found.");
        }
    }
    /*
    *//**
     * Responsible to call payment service to make payment from Guest to hotel
     * for reservation.
     * @param card , Card information on which payment going to made.
     * @param amount, Amount to be going to debit.
     * @param reservationId, reservation information for which payment is going to made.
     * @return String, returns SUCCESS or FAILURE of payment.
     *//*
    public String doPayment(ICard card, double amount, Long reservationId){
        PaymentPayload paymentPayload = new PaymentPayload(reservationId, amount, card) ;
        paymentService.sendPaymentRequest(paymentPayload);
        return paymentServiceProxy.doPayment(mapper.mapICardToProxy(card), amount);
    }

    *//**
     * It is fallback method get activated when actual Payment service is down or call
     * get failed due to timeout.
     * @param card
     * @param amount
     * @param reservationId
     * @return
     *//*
    @SuppressWarnings("unused")
    public String doPaymentFallBack(ICard card, double amount, Long reservationId){
        LOGGER.error("Payment Service is down while handling payment over card details: {}", card);

        return "SUCCESS";
    }*/

    /**
     * Responsible to fetch reservation information based on input provided.
     * @param id , For which information is going to fetch.
     * @param isDetailsRequired, Boolean flag to whether need fetch Guest as well as Hotel
     *                           information alon with reservation or not.
     *                           TURE: Hotel as well as Guest information get fetched.
     *                           FALSE: Only reservation information get fetched.
     * @return IReservation, Hold reservation information.
     * @throws ReservationEntityNotFoundException
     */
    public IReservation getReservation(Long id, boolean isDetailsRequired) throws ReservationEntityNotFoundException {
         ReservationEntity reservationEntity = reservationRepository.getReservationById(id);
         IReservation reservation = mapper.mapReservationDTOToIReservation(reservationEntity);
         if (isDetailsRequired){
             IHotel hotel = getHotelById(reservationEntity.getHotelId());
             com.reservation.proxy.model.guest.IGuest guest = getGuestById(reservationEntity.getGuestId());
             reservation.setHotel(hotel);
             reservation.setGuest(guest);
         }
         return reservation;
    }

    /**
     * Responsible to call Guest service to fetch guest information as well as reservation
     * IDs, those guest made reservation requests.
     * @param guestId , Id to which reservation information get fetched.
     * @return IGuest, Holds reservation as well as guest information.
     * @throws ReservationEntityNotFoundException
     */
    @Override
    public com.reservation.model.IGuest getReservationsByGuestId(Long guestId) throws ReservationEntityNotFoundException {
        com.reservation.proxy.model.guest.IGuest guest = getGuestById(guestId);

        List<IReservation> reservationList = new ArrayList<>();
        IMapper iMapper = mapper;
        ReservationRepository reservationRepository1 = reservationRepository;
        for (Long aLong : guest.getReservations()) {
            ReservationEntity reservationById = reservationRepository1.getReservationById(aLong);
            IReservation reservation = iMapper.mapReservationDTOToIReservation(reservationById);
            reservationList.add(reservation);
        }
        IGuest resultingGuest = mapper.mapProxyGuestToIGuest(guest);
        resultingGuest.setReservations(reservationList);
        return resultingGuest;
    }

}
