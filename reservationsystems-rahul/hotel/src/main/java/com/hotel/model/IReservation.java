package com.hotel.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hotel.model.implementation.Reservation;
import com.hotel.model.implementation.ReservationStatus;

import java.util.Date;

@JsonDeserialize(as = Reservation.class)
public interface IReservation {
    java.lang.Long getReservationId();
    void setState(ReservationStatus state);
    ReservationStatus getState();
    public IRoom getRoom() ;
    public void setRoom(IRoom room);
    public Long getGuestId();
    public void setGuestId(Long guest);
    public Date getFromDate();
    public void setFromDate(Date fromDate);
    public Date getToDate();
    public void setToDate(Date toDate);
    public void setReservationId(java.lang.Long reservationId);

    public String getRoomType();

    public void setRoomType(String roomType);

}
