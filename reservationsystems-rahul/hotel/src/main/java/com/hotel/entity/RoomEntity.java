package com.hotel.entity;

import com.hotel.model.implementation.RoomType;

import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.util.Objects;

@Embeddable
@Table(name = "Room")
public class RoomEntity {
    private int roomNo;
    private double rentPerNight;

    private RoomType roomType;

    private boolean isOccupiedCurrently = false;

    public RoomEntity() {
    }

    public RoomEntity(int roomNo, double rentPerNight, RoomType roomType) {
        this.roomNo = roomNo;
        this.rentPerNight = rentPerNight;
        this.roomType = roomType;
    }

    public boolean isOccupiedCurrently() {
        return isOccupiedCurrently;
    }

    public void setOccupiedCurrently(boolean occupiedCurrently) {
        isOccupiedCurrently = occupiedCurrently;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public double getRentPerNight() {
        return rentPerNight;
    }

    public void setRentPerNight(double rentPerNight) {
        this.rentPerNight = rentPerNight;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity room = (RoomEntity) o;
        return roomNo == room.roomNo && roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNo, roomType);
    }

}
