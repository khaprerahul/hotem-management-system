package com.hotel.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Reservation")
public class ReservationEntity {

    @JoinColumn(name = "roomNo")
    private RoomEntity room;

    private Long guestId;
    private Date fromDate;
    private Date toDate;

    @Id
    private Long reservationId;

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ReservationEntity() {
    }

    public ReservationEntity(RoomEntity room, Long guestId, Date fromDate, Date toDate, Long reservationId, String state, String single) {
        this.room = room;
        this.guestId = guestId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reservationId = reservationId;
        this.state = state;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
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
        ReservationEntity that = (ReservationEntity) o;
        return Objects.equals(reservationId, that.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId);
    }

}
