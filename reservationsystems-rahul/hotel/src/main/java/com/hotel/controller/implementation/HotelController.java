package com.hotel.controller.implementation;

import com.hotel.controller.IHotelController;
import com.hotel.model.IHotel;
import com.hotel.model.IReservation;
import com.hotel.model.implementation.Hotel;
import com.hotel.response.ApiResponseImpl;
import com.hotel.services.IHotelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class HotelController implements IHotelController {

    @Inject
    private IHotelService service;

    private <T> ApiResponseImpl<T> generateApiResponse(T actualData, HttpStatus status) {
        return new ApiResponseImpl<>(status, null, actualData);
    }

    @Override
    public ApiResponseImpl<IHotel> addNewHotel(Hotel hotel)
    {
        IHotel response = service.addNewHotel(hotel);
        return generateApiResponse(response, HttpStatus.CREATED);
    }

    @Override
    public ApiResponseImpl<IReservation> reservationRequest(IReservation reservation, Long hotelId){
        IReservation output = service.reservationRequest(hotelId, reservation);
        return generateApiResponse(output, HttpStatus.ACCEPTED);
    }

    @Override
    public ApiResponseImpl<IReservation> updateReservation(Long hotelId, IReservation reservation) {
        IReservation reservationResult = service.updateReservation(hotelId, reservation);
        return generateApiResponse(reservationResult, HttpStatus.ACCEPTED);
    }

    @Override
    public ApiResponseImpl<List<IReservation>> getAllReservationsByHotelId(Long hotelId){
        List<IReservation> reservations = service.getAllReservationsByHotelId(hotelId);
        return generateApiResponse(reservations,HttpStatus.OK);
    }

    @Override
    public ApiResponseImpl<List<IReservation>> getReservationByGuest(Long hotelId, Long guestId)
    {
        List<IReservation> reservations = service.getReservationByGuestIdPerHotel(hotelId, guestId);
        return generateApiResponse(reservations, HttpStatus.OK);
    }

    @Override
    public ApiResponseImpl<List<IHotel>> getHotels(List<Long> hotelIds){
        List<IHotel> hotels = service.getHotels(hotelIds);
        return generateApiResponse(hotels, HttpStatus.OK);
    }

    @Override
    public ApiResponseImpl<IHotel> getHotelById(Long hotelId){
        IHotel hotel = service.getHotelById(hotelId);
        return generateApiResponse(hotel, HttpStatus.OK);
    }

    @Override
    public ApiResponseImpl<List<IHotel>> searchHotels(String cityName) {
        List<IHotel> hotels = service.searchHotelsByCity(cityName);
        return generateApiResponse(hotels, HttpStatus.OK);
    }

}