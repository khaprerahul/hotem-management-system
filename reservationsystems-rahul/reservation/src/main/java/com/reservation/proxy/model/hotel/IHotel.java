package com.reservation.proxy.model.hotel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reservation.proxy.model.hotel.implementation.Hotel;

@JsonDeserialize(as = Hotel.class)
public interface IHotel {
    public Long getHotelId();

    public void setHotelId(Long hotelId);

    public String getName() ;

    public void setName(String name);

    public String getPhoneNumber();

    public void setPhoneNumber(String phoneNumber);

    public int getStarRatting() ;

    public void setStarRatting(int starRatting);

    public IAddress getAddress();

    public void setAddress(IAddress address);
}
