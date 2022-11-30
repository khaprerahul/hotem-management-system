package com.reservation.proxy.model.hotel.implementation;

import com.reservation.proxy.model.hotel.IAddress;
import com.reservation.proxy.model.hotel.IHotel;

public class Hotel implements IHotel {

    private Long hotelId;
    private String name;
    private String phoneNumber;
    private int starRatting;
    private IAddress address;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStarRatting() {
        return starRatting;
    }

    public void setStarRatting(int starRatting) {
        this.starRatting = starRatting;
    }

    public IAddress getAddress() {
        return address;
    }

    public void setAddress(IAddress address) {
        this.address = address;
    }

    public Hotel() {
    }

    public Hotel(Long hotelId, String name, String phoneNumber, int starRatting, IAddress address) {
        this.hotelId = hotelId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.starRatting = starRatting;
        this.address = address;
    }
}
