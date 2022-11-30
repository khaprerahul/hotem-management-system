package com.hotel.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hotel.model.implementation.Room;
import com.hotel.model.implementation.RoomType;
@JsonDeserialize(as = Room.class)
public interface IRoom {
    public int getRoomNo();

    public void setRoomNo(int roomNo);

    public double getRentPerNight();

    public void setRentPerNight(double rentPerNight);

    public RoomType getRoomType();

    public void setRoomType(RoomType roomType);

}
