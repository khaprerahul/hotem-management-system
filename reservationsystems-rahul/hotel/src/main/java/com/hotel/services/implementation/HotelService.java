package com.hotel.services.implementation;

import com.hotel.entity.HotelEntity;
import com.hotel.entity.ReservationEntity;
import com.hotel.mapper.IMapper;
import com.hotel.model.IHotel;
import com.hotel.model.IReservation;
import com.hotel.model.IRoom;
import com.hotel.model.implementation.Hotel;
import com.hotel.repository.implementation.HotelRepositoryImpl;
import com.hotel.repository.implementation.ReservationRepositoryImpl;
import com.hotel.services.IHotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotelService implements IHotelService {

    @Autowired
    private HotelRepositoryImpl hotelRepository;

    @Autowired
    private ReservationRepositoryImpl reservationRepository;

    @Inject
    private IMapper imapper;

    private final static Logger LOG = LoggerFactory.getLogger(HotelService.class);

    public IHotel addNewHotel(IHotel hotel)
    {
        LOG.debug("HotelService :: addNewHotel :: Request to add new hotel with information {}", hotel);
        HotelEntity hotelEntity = imapper.mapIHotelToHotelDTO(hotel);
        HotelEntity saved = hotelRepository.save(hotelEntity);
        return imapper.mapHotelDTOToIHotel(saved);
    }

    public List<IReservation> getReservationByGuestIdPerHotel(Long hotelId, Long guestId ){
        LOG.debug("HotelService :: getReservationByGuestIdPerHotel :: Request to get reservations for guest id  {}", guestId);
        HotelEntity hotelEntity = hotelRepository.findById(hotelId);
        List<IReservation> reservations = hotelEntity.getReservations().stream().filter(reservationDTO -> reservationDTO.getGuestId().equals(guestId))
                    .map(imapper::mapReservationDTOToIReservation).collect(Collectors.toList());
        LOG.debug("HotelService :: getReservationByGuestIdPerHotel :: reservations for guest are {}", reservations);
        return reservations;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public IReservation reservationRequest(Long hotelId, IReservation reservation){
        LOG.debug("HotelService :: cancelReservation :: Request to reservation {}", reservation);
        HotelEntity hotel = hotelRepository.findById(hotelId);
        reservation.setRoom(findAvailableRoom(imapper.mapHotelDTOToIHotel(hotel), reservation.getFromDate(), reservation.getToDate()));
        hotel.getReservations().add(imapper.mapIReservationToReservationDTO(reservation));
        return reservation;
    }

    private IRoom findAvailableRoom(IHotel hotel, Date fromDate, Date toDate){
        List<IRoom> reservedRooms = hotel.getReservations().stream().filter(iReservation -> iReservation.getFromDate().equals(fromDate))
                .map(iReservation -> iReservation.getRoom()).collect(Collectors.toList());

        Optional<IRoom> availableRoom = hotel.getRooms().stream().filter(room -> ! reservedRooms.contains(room)).findFirst();

        return availableRoom.isPresent() ? availableRoom.get():null;
    }

    public List<IReservation> getAllReservationsByHotelId(Long hotelId){
        HotelEntity hotel = hotelRepository.findById(hotelId);
        return hotel.getReservations().stream().map(imapper:: mapReservationDTOToIReservation).collect(Collectors.toList());
    }

    public List<IHotel> getHotels(List<Long> hotelIds){
        LOG.debug("HotelService :: getHotels :: Request to retrieve hotel information {}", hotelIds);
        List<IHotel> hotels =  new ArrayList<>();
        for(Long hotelId : hotelIds){
            HotelEntity hotel = hotelRepository.findById(hotelId);
            hotels.add(imapper.mapHotelDTOToIHotel(hotel));
        }
        LOG.debug("HotelService :: getHotels :: returning hotel information {}", hotels);
        return hotels;
    }

    public IHotel getHotelById(Long hotelId){
        LOG.debug("HotelService :: getHotels :: Request to retrieve hotel information {}", hotelId);
        HotelEntity hotelEntity =  hotelRepository.findById(hotelId);
        IHotel hotel = imapper.mapHotelDTOToIHotel(hotelEntity);
        LOG.debug("HotelService :: getHotels :: returning hotel information {}", hotel);
        return hotel;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public IReservation updateReservation(Long hotelId, IReservation reservation) {
        LOG.debug("HotelService :: updateReservation :: Request to update reservation {} ", reservation);
        ReservationEntity reservationEntity = reservationRepository.findReservationById(reservation.getReservationId());
        reservationEntity.setState(reservation.getState().toString());
        return imapper.mapReservationDTOToIReservation(reservationEntity);
    }

    @Override
    public List<IHotel> searchHotelsByCity(String cityName) {
        LOG.debug("HotelService :: searchHotelsByCity :: Request to search hotels in {} ", cityName);
        List<HotelEntity> allHotelsEntity = hotelRepository.getAllHotels();
        List<IHotel> resultingHotels = allHotelsEntity.stream().filter(h -> h.getAddress().getCity().equalsIgnoreCase(cityName))
                                        .map(imapper :: mapHotelDTOToIHotel)
                                        .collect(Collectors.toList());

        LOG.debug("HotelService :: searchHotelsByCity :: Resulting hotels {} ", resultingHotels);
        return resultingHotels;
    }


}
