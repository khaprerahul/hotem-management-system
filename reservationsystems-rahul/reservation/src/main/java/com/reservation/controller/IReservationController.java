package com.reservation.controller;

import com.reservation.exception.ReservationEntityNotFoundException;
import com.reservation.model.ICard;
import com.reservation.model.IGuest;
import com.reservation.model.IReservation;
import com.reservation.response.ApiResponseImpl;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller APIs to make, update and retrieve reservation information.
 */
@Api("Controller APIs to make, update and retrieve reservation information.")
@RequestMapping("/reservations")
public interface IReservationController {

    /**
     * @param id , reservation ID to fetch information.
     * @param isDetailsRequired, boolean flag to fetch Hotel and Guest information along with reservation information.
     * @return IReservation, holds information of reservation as well as hotel and guest.
     * @throws ReservationEntityNotFoundException
     */
    @ApiOperation(value = "To get reservation information by id.",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = ApiResponseImpl.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Reservation fetched successfully.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "Not authorized to do this operation", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Entity not found in Reservation/from downstream services.", response = ApiResponseImpl.class),
            @ApiResponse(code = 500, message = "Unwanted exception from this/downstream service.", response = ApiResponseImpl.class)
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseImpl<IReservation> getReservation(@PathVariable("id") Long id, @RequestParam("isDetailsRequired") boolean isDetailsRequired) throws ReservationEntityNotFoundException;

    /**
     * @param reservation , reservation information to store.
     * @return IReservation, hold reservation information that get stored.
     */
    @ApiOperation(  value = "Create new reservation.",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    response = ApiResponseImpl.class
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "Reservation created successfully.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "Not authorized to do this operation", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Entity not found in Reservation/from downstream services.", response = ApiResponseImpl.class),
            @ApiResponse(code = 500, message = "Unwanted exception from this/downstream service.", response = ApiResponseImpl.class)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ApiResponseImpl<IReservation> reservationRequest(@RequestBody IReservation reservation);

    /**
     * @param reservation , reservation information to update.
     * @return IReservation, reservation information that get updated.
     * @throws ReservationEntityNotFoundException
     */
    @ApiOperation(  value = "Update reservation.",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    response =  ApiResponseImpl.class
    )
    @ApiResponses({
            @ApiResponse(code = 202, message = "Reservation updated successfully.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "Not authorized to do this operation", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Entity not found in Reservation/from downstream services.", response = ApiResponseImpl.class),
            @ApiResponse(code = 500, message = "Unwanted exception from this/downstream service.", response = ApiResponseImpl.class)
    })
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ApiResponseImpl<IReservation> updateReservation(@RequestBody IReservation reservation) throws ReservationEntityNotFoundException;

    /**
     * @param guestId , guest id for which reservation information needs to fetch.
     * @return IGuest, Holds information of Guest as well as Reservations that guest made.
     */
    @ApiOperation(value = "Get reservation history for Guest",
                    produces = MediaType.APPLICATION_JSON_VALUE,
                    response = ApiResponseImpl.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Reservation information fetched successfully.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "Not authorized to do this operation", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Entity not found in Reservation/from downstream services.", response = ApiResponseImpl.class),
            @ApiResponse(code = 500, message = "Unwanted exception from this/downstream service.", response = ApiResponseImpl.class)
    })
    @GetMapping(value = "/guests/{guestId}")
    @PreAuthorize("hasRole('GUEST')")
    public ApiResponseImpl<IGuest> getReservationsByGuestId(@PathVariable("guestId") Long guestId) throws ReservationEntityNotFoundException;

    @GetMapping(value = "/guests/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ApiResponseImpl<IGuest> getGuest(@PathVariable("id") Long id);

    @PostMapping(value = "/{reservationId}/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public String doPayment(@RequestBody ICard card, @RequestParam("amount") double amount, @PathVariable("reservationId") Long reservationId);

}
