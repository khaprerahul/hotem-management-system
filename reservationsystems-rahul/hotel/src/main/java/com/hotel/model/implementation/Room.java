package com.hotel.model.implementation;

import com.hotel.model.IRoom;

import java.util.Objects;

public class Room implements IRoom {

    private int roomNo;
    private double rentPerNight;
    private RoomType roomType;

    private boolean isOccupiedCurrently = false;

    public Room() {
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
        Room room = (Room) o;
        return roomNo == room.roomNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNo);
    }
}
