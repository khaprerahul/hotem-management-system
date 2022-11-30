package com.hotel.services;

import com.hotel.model.IHotel;
import com.hotel.model.IReservation;

import java.util.Date;
import java.util.List;

public interface IHotelService {
    IHotel addNewHotel(IHotel hotel);
    IReservation reservationRequest(Long hotelId, IReservation reservation);
    List<IReservation> getAllReservationsByHotelId(Long hotelId);
    List<IReservation> getReservationByGuestIdPerHotel(Long hotelId, Long guestId );


    public List<IHotel> getHotels(List<Long> hotelIds);
    public IHotel getHotelById(Long hotelId);

    IReservation updateReservation(Long hotelId, IReservation reservation);

    List<IHotel> searchHotelsByCity(String cityName);
}


