package com.guest.model.implementation;

import com.guest.model.ICard;
import com.guest.model.IGuest;

import java.util.ArrayList;
import java.util.List;


public class Guest implements IGuest {
    private Long guestId;
    private String name;
    private String email;
    private String contactNumber;
    private int ratting;
    private List<Long> reservations =  new ArrayList<>();

    private List<ICard> cards = new ArrayList<>();

    public List<Long> getReservations() {
        return reservations;
    }

    public void setReservations(List<Long> reservations) {
        this.reservations = reservations;
    }

    public List<ICard> getCards() {
        return cards;
    }

    public void setCards(List<ICard> cards) {
        this.cards = cards;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getRatting() {
        return ratting;
    }

    public void setRatting(int ratting) {
        this.ratting = ratting;
    }

    public Guest(){}

    public Guest(Long guestId, String name, String email, String contactNumber) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", ratting=" + ratting +
                ", reservations=" + reservations +
                ", cards=" + cards +
                '}';
    }
}
