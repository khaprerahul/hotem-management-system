package com.reservation.proxy.model.hotel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reservation.proxy.model.hotel.implementation.Address;

@JsonDeserialize(as = Address.class)
public interface IAddress {
    public Long getAddressId();

    public void setAddressId(Long addressId);

    public String getStreet();

    public void setStreet(String street);

    public String getArea();

    public void setArea(String area);

    public String getCity();

    public void setCity(String city);
}
