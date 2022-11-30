package com.reservation.proxy;

import com.reservation.model.IReservation;
import com.reservation.proxy.model.hotel.IHotel;
import com.reservation.response.ApiResponseImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${zuul.service.name}")
public interface IHotelInformationProxy {
    @GetMapping(value = "/HotelService/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseImpl<List<IHotel>> getHotels(@RequestParam("hotelIds") List<Long> hotelIds);

    @GetMapping(value = "/HotelService/hotels/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseImpl<IHotel> getHotelById(@PathVariable("hotelId") Long hotelId);

    @PostMapping(value = "/HotelService/hotels/{hotelId}/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseImpl<IReservation> reservationRequest(@RequestBody IReservation reservation, @PathVariable("hotelId") Long hotelId);

    @PutMapping(value = "/HotelService/hotels/{hotelId}/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseImpl<IReservation> updateReservation(@PathVariable("hotelId") Long hotelId,@RequestBody IReservation reservation);
}
