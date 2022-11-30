package com.reservation.model.implementation;

import com.reservation.model.ICard;
import com.reservation.model.IReservation;
import com.reservation.proxy.model.guest.IGuest;
import com.reservation.proxy.model.hotel.IHotel;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation implements IReservation {
    private Date fromDate;
    private Date toDate;
    private Long guestId;
    private Long hotelId;
    private Long reservationId;
    private ReservationStatus state;
    private String roomType;
    private IGuest guest;
    private IHotel hotel;
    private double amount;

    private ICard card;


    public Reservation(Date fromDate, Date toDate, Long guestId, Long hotelId, Long reservationId, ReservationStatus state, String roomType, ICard card) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.guestId = guestId;
        this.hotelId = hotelId;
        this.reservationId = reservationId;
        this.state = state;
        this.roomType = roomType;
        this.card = card;
    }

}
