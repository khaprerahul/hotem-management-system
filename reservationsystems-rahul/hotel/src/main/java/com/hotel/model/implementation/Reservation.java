package com.hotel.model.implementation;

import com.hotel.model.IReservation;
import com.hotel.model.IRoom;
import java.util.Date;
import java.util.Objects;


public class Reservation implements IReservation {
    private IRoom room;
    private Long guestId;
    private Date fromDate;
    private Date toDate;
    private Long reservationId;
    private ReservationStatus state;
    private String roomType;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public ReservationStatus getState() {
        return state;
    }

    public void setState(ReservationStatus state) {
        this.state = state;
    }

    public Reservation(){}
    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(reservationId, that.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId);
    }

    public Reservation(IRoom room, Long guestId, Date fromDate, Date toDate, Long reservationId, ReservationStatus state, String roomType) {
        this.room = room;
        this.guestId = guestId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reservationId = reservationId;
        this.state = state;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "room=" + room +
                ", guestId=" + guestId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", reservationId=" + reservationId +
                ", state='" + state + '\'' +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}
