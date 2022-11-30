package com.reservation.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reservation.model.implementation.Reservation;
import com.reservation.model.implementation.ReservationStatus;
import com.reservation.proxy.model.hotel.IHotel;
import java.util.Date;

@JsonDeserialize(as = Reservation.class)
public interface IReservation {
    public double getAmount();

    public void setAmount(double amount);

    public ICard getCard();

    public void setCard(ICard card);

    public Date getFromDate();

    public void setFromDate(Date fromDate);

    public Date getToDate();

    public void setToDate(Date toDate);

    public Long getGuestId();

    public void setGuestId(Long guestId);

    public Long getHotelId();

    public void setHotelId(Long hotelId);

    public Long getReservationId();

    public void setReservationId(Long reservationId);

    public ReservationStatus getState();

    public void setState(ReservationStatus state);

    public String getRoomType();

    public void setRoomType(String roomType);

    public com.reservation.proxy.model.guest.IGuest getGuest();

    public void setGuest(com.reservation.proxy.model.guest.IGuest guest);

    public IHotel getHotel();

    public void setHotel(IHotel hotel);
}
