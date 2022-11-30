package com.guest.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guest")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long guestId;
    private String name;
    private String email;
    private String contactNumber;
    private int ratting;

    @ElementCollection
    @CollectionTable(name = "Card", joinColumns = @JoinColumn(name = "guest_id"))
    private List<CardEntity> cards = new ArrayList();

    @ElementCollection
    private List<Long> reservations =  new ArrayList();

    public List<Long> getReservations() {
        return reservations;
    }

    public void setReservations(List<Long> reservations) {
        this.reservations = reservations;
    }

    /*@ElementCollection
    @CollectionTable(name = "Stay", joinColumns = @JoinColumn(name = "guest_id"))
    private List<StayDTO> stayList = new ArrayList();*/

    public List<CardEntity> getCards() {
        return cards;
    }

    public void setCards(List<CardEntity> cards) {
        this.cards = cards;
    }

   /* public List<StayDTO> getStayList() {
        return stayList;
    }

    public void setStayList(List<StayDTO> stayList) {
        this.stayList = stayList;
    }
*/
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

    public GuestEntity(){}

    public GuestEntity(Long guestId, String name, String email, String contactNumber) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
    }
}
