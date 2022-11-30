package com.guest.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.guest.model.implementation.Guest;

import java.util.List;

@JsonDeserialize(as = Guest.class)
public interface IGuest {

    public List<Long> getReservations();

    public void setReservations(List<Long> reservations);

    public Long getGuestId();

    public void setGuestId(Long guestId);

    public String getName();

    public void setName(String name);

    public String getEmail();

    public void setEmail(String email);

    public String getContactNumber();

    public void setContactNumber(String contactNumber);

    public int getRatting();

    public void setRatting(int ratting);

    /*public List<IStay> getStayList();

    public void setStayList(List<IStay> stayList);*/

    public List<ICard> getCards();

    public void setCards(List<ICard> cards);
}
