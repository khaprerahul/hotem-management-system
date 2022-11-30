package com.hotel.controller;

import com.hotel.model.IHotel;
import com.hotel.model.IReservation;
import com.hotel.model.implementation.Hotel;
import com.hotel.response.ApiResponseImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Store, retrieve and update Hotel information.")
public interface IHotelController {

    @ApiOperation(value = "Add new Hotel.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Hotel information stored successfully.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "Not authorized to do this operation", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Entity not found in Hotel service.", response = ApiResponseImpl.class),
            @ApiResponse(code = 500, message = "Unwanted exception from Hotel service.", response = ApiResponseImpl.class)
    })
    @PostMapping(value = "/hotels",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public ApiResponseImpl<IHotel> addNewHotel(@RequestBody Hotel hotel);

    @ApiOperation(value = "Attach reservation request with hotel.")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Reservation information stored successfully.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "Not authorized to do this operation", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Entity not found in Hotel service.", response = ApiResponseImpl.class),
            @ApiResponse(code = 500, message = "Unwanted exception from Hotel service.", response = ApiResponseImpl.class)
    })
    @PostMapping(value = "/hotels/{hotelId}/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ApiResponseImpl<IReservation> reservationRequest(@RequestBody IReservation reservation, @PathVariable("hotelId") Long hotelId);

    @ApiOperation(value = "Update reservation.")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Reservation information updated successfully.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "Not authorized to do this operation", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Entity not found in Hotel service.", response = ApiResponseImpl.class),
            @ApiResponse(code = 500, message = "Unwanted exception from Hotel service.", response = ApiResponseImpl.class)
    })
    @PutMapping(value = "/hotels/{hotelId}/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ApiResponseImpl<IReservation> updateReservation(@PathVariable("hotelId") Long hotelId, @RequestBody IReservation reservation);

    @ApiOperation(value = "Get all reservations by hotel id.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Reservation information fetched successfully.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "Not authorized to do this operation", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Entity not found in Hotel service.", response = ApiResponseImpl.class),
            @ApiResponse(code = 500, message = "Unwanted exception from Hotel service.", response = ApiResponseImpl.class)
    })
    @GetMapping(value = "/hotels/{hotelId}/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public ApiResponseImpl<List<IReservation>> getAllReservationsByHotelId(@PathVariable("hotelId") Long hotelId);

    @ApiOperation(value = "Get all reservations for guest by hotel id.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Reservation information fetched successfully.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "Not authorized to do this operation", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Entity not found in Hotel service.", response = ApiResponseImpl.class),
            @ApiResponse(code = 500, message = "Unwanted exception from Hotel service.", response = ApiResponseImpl.class)
    })
    @GetMapping(value = "/hotels/{hotelId}/{guestId}/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public ApiResponseImpl<List<IReservation>> getReservationByGuest(@PathVariable("hotelId") Long hotelId, @PathVariable("guestId") Long guestId);

    @ApiOperation(value = "Retrieve hotels information provided by input list.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Hotel information fetched successfully.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "Not authorized to do this operation", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Entity not found in Hotel service.", response = ApiResponseImpl.class),
            @ApiResponse(code = 500, message = "Unwanted exception from Hotel service.", response = ApiResponseImpl.class)
    })
    @GetMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseImpl<List<IHotel>> getHotels(@RequestParam("hotelIds") List<Long> hotelIds);

    @ApiOperation(value = "Get hotel information by hotel id.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Hotel information fetched successfully.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "Not authorized to do this operation", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Entity not found in Hotel service.", response = ApiResponseImpl.class),
            @ApiResponse(code = 500, message = "Unwanted exception from Hotel service.", response = ApiResponseImpl.class)
    })
    @GetMapping(value = "/hotels/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('HOTEL', 'GUEST')")
    public ApiResponseImpl<IHotel> getHotelById(@PathVariable("hotelId") Long hotelId);

    @ApiOperation(value = "Search hotels per City.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Hotel information fetched successfully.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "Not authorized to do this operation", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Entity not found in Hotel service.", response = ApiResponseImpl.class),
            @ApiResponse(code = 500, message = "Unwanted exception from Hotel service.", response = ApiResponseImpl.class)
    })
    @GetMapping(value = "/{cityName}/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ApiResponseImpl<List<IHotel>> searchHotels(@PathVariable("cityName") String cityName );
}
