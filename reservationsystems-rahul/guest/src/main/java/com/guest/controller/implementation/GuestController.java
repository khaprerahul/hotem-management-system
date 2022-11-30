package com.guest.controller.implementation;

import com.guest.controller.IGuestController;
import com.guest.model.ICard;
import com.guest.model.IGuest;
import com.guest.response.ApiResponseImpl;
import com.guest.service.IGuestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class GuestController implements IGuestController {

    @Inject
    private IGuestService service;

    private <T> ApiResponseImpl<T> generateApiResponse(T actualData, HttpStatus status) {
        return new ApiResponseImpl(status, null, actualData);
    }

    @Override
    public ApiResponseImpl<IGuest> addNewGuest(IGuest guest) {
        IGuest response = service.addNewGuest(guest);
        return generateApiResponse(response, HttpStatus.CREATED);
    }

    @Override
    public ApiResponseImpl<IGuest> getGuest(Long guestId) {
        IGuest guest = service.getGuest(guestId);
        return generateApiResponse(guest, HttpStatus.OK);
    }

    @Override
    public ApiResponseImpl<IGuest> addStayByGuest(Long guestId, Long stay){
        IGuest guest = service.addStayByGuest(guestId, stay);
        return generateApiResponse(guest, HttpStatus.ACCEPTED);
    }

    @Override
    public ApiResponseImpl<List<IGuest>> getGuests(List<Long> guestIds){
        List<IGuest> guests = service.getGuests(guestIds);
        return generateApiResponse(guests, HttpStatus.OK);
    }

    @Override
    public ApiResponseImpl<IGuest> addNewCard(Long guestId, ICard card){
        IGuest guest = service.addNewCard(guestId, card);
        return generateApiResponse(guest, HttpStatus.OK);
    }
}
