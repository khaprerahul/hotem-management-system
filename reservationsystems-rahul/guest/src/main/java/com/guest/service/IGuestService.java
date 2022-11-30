package com.guest.service;

import com.guest.model.ICard;
import com.guest.model.IGuest;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface IGuestService {

    public IGuest addNewGuest(IGuest guest);

    public IGuest getGuest(Long id) throws EntityNotFoundException;

    public IGuest addStayByGuest(Long guestId, Long stay) throws EntityNotFoundException;

    public List<IGuest> getGuests(List<Long> guestIds ) throws EntityNotFoundException;

    IGuest addNewCard(long guestId, ICard card);
}
