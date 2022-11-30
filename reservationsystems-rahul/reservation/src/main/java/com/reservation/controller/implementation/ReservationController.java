package com.reservation.controller.implementation;

import com.reservation.controller.IReservationController;
import com.reservation.exception.ReservationEntityNotFoundException;
import com.reservation.model.ICard;
import com.reservation.model.IGuest;
import com.reservation.model.IReservation;

import com.reservation.proxy.IHotelInformationProxy;
import com.reservation.response.ApiResponseImpl;
import com.reservation.service.IPaymentService;
import com.reservation.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

/**
 * Reservation service Controller implementation.
 */
@RestController
public class ReservationController implements IReservationController {

    @Inject
    private IReservationService reservationService;

    @Autowired
    private IPaymentService iPaymentService;

    @Override
    public ApiResponseImpl<IReservation> getReservation(Long id, boolean isDetailsRequired) throws ReservationEntityNotFoundException {
        IReservation reservation = reservationService.getReservation(id, isDetailsRequired);
        return generateApiResponse(reservation, HttpStatus.OK);
    }

    private <T> ApiResponseImpl<T> generateApiResponse(T actualData, HttpStatus status) {
        return new ApiResponseImpl<T>(status, null, actualData);
    }

    @Override
    public ApiResponseImpl<IReservation> reservationRequest(IReservation reservation) {
        IReservation saved = reservationService.requestForReservation(reservation);
        return generateApiResponse(saved, HttpStatus.CREATED);

    }

    @Override
    public ApiResponseImpl<IReservation> updateReservation(IReservation reservation) throws ReservationEntityNotFoundException {
        IReservation update = reservationService.updateReservation(reservation);
        return generateApiResponse(update, HttpStatus.ACCEPTED);
    }

    @Override
    public ApiResponseImpl<IGuest> getReservationsByGuestId(Long guestId) throws ReservationEntityNotFoundException {
        IGuest guest = reservationService.getReservationsByGuestId(guestId);
        return generateApiResponse(guest, HttpStatus.OK);
    }

    @Autowired
    private IHotelInformationProxy hotelProxy;

    @Override
    public ApiResponseImpl getGuest(Long id){

        com.reservation.proxy.model.guest.IGuest guest = reservationService.getGuestById(id);

        return generateApiResponse(guest, HttpStatus.OK);

        /*RestTemplate restTemplate = new RestTemplate();
        String getGuestURL = "http://localhost:8081/guests";
        return restTemplate.getForObject(getGuestURL+"/"+id,ApiResponseImpl.class);*/
    }

    @Override
    public String doPayment(ICard card, double amount, Long reservationId) {
        return iPaymentService.doPayment(card, amount,reservationId);
    }

}
