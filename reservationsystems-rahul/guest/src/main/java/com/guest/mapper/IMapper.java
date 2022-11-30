package com.guest.mapper;

import com.guest.entity.CardEntity;
import com.guest.entity.GuestEntity;
import com.guest.model.ICard;
import com.guest.model.IGuest;

public interface IMapper {
    public IGuest mapGuestDTOToIGuest(GuestEntity guestEntity);

    public GuestEntity mapIGuestToGuestDTO(IGuest iGuest);

    public CardEntity mapICardToCardDTO(ICard iCard);
}
