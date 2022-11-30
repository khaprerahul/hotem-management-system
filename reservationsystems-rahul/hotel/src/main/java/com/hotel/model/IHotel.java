package com.hotel.model;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hotel.model.implementation.Hotel;

import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonDeserialize(as = Hotel.class)
public interface IHotel {
    Long getHotelId();
    List<IReservation> getReservations();
    public void setHotelId(Long hotelId);
    public String getName();
    public void setName(String name);
    public String getPhoneNumber();
    public void setPhoneNumber(String phoneNumber);
    public int getStarRatting();
    public void setStarRatting(int starRatting);
    public IAddress getAddress();
    public void setAddress(IAddress address);
    public List<IRoom> getRooms();
    public void setRooms(List<IRoom> rooms);
    public void setReservations(List<IReservation> reservation);
    public Map<Date, List<Long>> getReservationsByDate();
    public void setReservationsByDate(Map<Date, List<Long>> reservationsByDate);

}
