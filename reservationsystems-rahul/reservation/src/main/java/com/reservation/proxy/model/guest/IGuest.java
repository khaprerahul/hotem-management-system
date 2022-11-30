package com.reservation.proxy.model.guest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reservation.proxy.model.guest.implementation.Guest;
import com.reservation.proxy.model.payment.ICard;

import java.util.List;

@JsonDeserialize(as = Guest.class)
public interface IGuest {

    public Long getGuestId();

    public void setGuestId(Long guestId);

    public String getName();

    public void setName(String name) ;

    public String getEmail() ;

    public void setEmail(String email);

    public String getContactNumber();

    public void setContactNumber(String contactNumber);

    public int getRatting();

    public void setRatting(int ratting);

    public List<ICard> getCard();

    public void setCard(List<ICard> card);

    public void setReservations(List<Long> reservations);

    public List<Long> getReservations();
}
