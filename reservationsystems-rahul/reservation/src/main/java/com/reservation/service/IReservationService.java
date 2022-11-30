package com.reservation.service;

import com.reservation.exception.ReservationEntityNotFoundException;
import com.reservation.model.ICard;
import com.reservation.model.IReservation;
import com.reservation.proxy.model.hotel.IHotel;


public interface IReservationService {
    public com.reservation.proxy.model.guest.IGuest getGuestById(Long guestId) ;

    public IHotel getHotelById(Long hotelId);

    public IReservation requestForReservation(IReservation reservation);

    public IReservation updateReservation(IReservation reservation) throws ReservationEntityNotFoundException;

    //public String doPayment(ICard card, double amount, Long reservationId);

    public IReservation getReservation(Long id, boolean isDetailsRequired) throws ReservationEntityNotFoundException;

    com.reservation.model.IGuest getReservationsByGuestId(Long guestId) throws ReservationEntityNotFoundException;

}
