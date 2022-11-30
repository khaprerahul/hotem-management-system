package com.guest.mapper.implementation;

import com.guest.entity.*;
import com.guest.mapper.IMapper;
import com.guest.model.*;
import com.guest.model.implementation.*;

import java.util.stream.Collectors;

public class Mapper implements IMapper {
    @Override
    public IGuest mapGuestDTOToIGuest(GuestEntity guestEntity) {
        IGuest iGuest =  new Guest();
        iGuest.setGuestId(guestEntity.getGuestId());
        iGuest.setContactNumber(guestEntity.getContactNumber());
        iGuest.setEmail(guestEntity.getEmail());
        iGuest.setName(guestEntity.getName());
        iGuest.setRatting(guestEntity.getRatting());
        iGuest.setCards(guestEntity.getCards().stream().map(this::mapCardDTOToICard).collect(Collectors.toList()));
        iGuest.setReservations(guestEntity.getReservations());
        return iGuest;
    }

    @Override
    public GuestEntity mapIGuestToGuestDTO(IGuest iGuest) {
        GuestEntity guestEntity =  new GuestEntity();
        guestEntity.setGuestId(iGuest.getGuestId());
        guestEntity.setName(iGuest.getName());
        guestEntity.setEmail(iGuest.getEmail());
        guestEntity.setRatting(iGuest.getRatting());
        guestEntity.setContactNumber(iGuest.getContactNumber());
        guestEntity.setCards(iGuest.getCards().stream().map(this::mapICardToCardDTO).collect(Collectors.toList()));
        guestEntity.setReservations(iGuest.getReservations());
        return guestEntity;
    }

    public ICard mapCardDTOToICard(CardEntity cardEntity){
        ICard iCard = new Card();
        iCard.setCardNumber(cardEntity.getCardNumber());
        iCard.setExpMonth(cardEntity.getExpMonth());
        iCard.setExpYear(cardEntity.getExpYear());
        return iCard;
    }

    public CardEntity mapICardToCardDTO(ICard iCard){
        CardEntity cardEntity =  new CardEntity();
        cardEntity.setCardNumber(iCard.getCardNumber());
        cardEntity.setExpMonth(iCard.getExpMonth());
        cardEntity.setExpYear(iCard.getExpYear());
        return cardEntity;
    }
}
