package com.guest.controller;

import com.guest.model.ICard;
import com.guest.model.IGuest;
import com.guest.response.ApiResponseImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "guestInformation", description = "APIs to store, retrieve and update Guest information")
@RequestMapping("/guests")
public interface IGuestController {

    @ApiOperation(value = "Add new Guest information.", response = IGuest.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Guest information successfully stored.", response = ApiResponseImpl.class),
            @ApiResponse(code = 401, message = "Authentication failed.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "You are not authorized to do this operation.", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ApiResponseImpl.class)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ApiResponseImpl<IGuest> addNewGuest(@RequestBody IGuest guest);

    @ApiOperation(value = "Get guest information.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Guest information successfully fetched.", response = ApiResponseImpl.class),
            @ApiResponse(code = 401, message = "Authentication failed.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "You are not authorized to do this operation.", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ApiResponseImpl.class)
    })
    @GetMapping(value = "/{guestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ApiResponseImpl<IGuest> getGuest(@PathVariable("guestId") Long guestId);

    @ApiOperation(value = "Add stay for Guest.")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Stay information successfully stored.", response = ApiResponseImpl.class),
            @ApiResponse(code = 401, message = "Authentication failed.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "You are not authorized to do this operation.", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ApiResponseImpl.class)
    })
    @PutMapping(value = "/{guestId}/stay", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ApiResponseImpl<IGuest> addStayByGuest(@PathVariable("guestId") Long guestId, @RequestParam("reservationId") Long stay);

    @ApiOperation(value = "Get guests information for given list input.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Guest information successfully fetched.", response = ApiResponseImpl.class),
            @ApiResponse(code = 401, message = "Authentication failed.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "You are not authorized to do this operation.", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ApiResponseImpl.class)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseImpl<List<IGuest>> getGuests(@RequestParam("guestId") List<Long> guestIds);

    @ApiOperation(value = "Add card for guest.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Card information successfully stored.", response = ApiResponseImpl.class),
            @ApiResponse(code = 401, message = "Authentication failed.", response = ApiResponseImpl.class),
            @ApiResponse(code = 403, message = "You are not authorized to do this operation.", response = ApiResponseImpl.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ApiResponseImpl.class)
    })
    @PreAuthorize("hasRole('GUEST')")
    @PostMapping(value = "/{guestId}/card", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseImpl<IGuest> addNewCard(@PathVariable("guestId") Long guestId, @RequestBody ICard card);
}
