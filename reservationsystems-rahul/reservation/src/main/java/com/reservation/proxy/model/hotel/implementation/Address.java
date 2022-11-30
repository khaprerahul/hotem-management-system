package com.reservation.proxy.model.hotel.implementation;

import com.reservation.proxy.model.hotel.IAddress;

public class Address implements IAddress {

    private Long addressId;
    private String street;
    private String area;
    private String city;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address() {
    }

    public Address(Long addressId, String street, String area, String city) {
        this.addressId = addressId;
        this.street = street;
        this.area = area;
        this.city = city;
    }
}
