package com.hotel.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Hotel")
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelId;
    private String name;
    private String phoneNumber;
    private int starRatting;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    private AddressEntity address;

    @ElementCollection
    @CollectionTable(name = "Room", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "rooms")
    private List<RoomEntity> rooms = new ArrayList<>();

    @Column(name = "reservations")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservationId")
    private List<ReservationEntity> reservations = new ArrayList<>();

    public void setReservations(List<ReservationEntity> reservations) {
        this.reservations = reservations;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStarRatting() {
        return starRatting;
    }

    public void setStarRatting(int starRatting) {
        this.starRatting = starRatting;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    public HotelEntity() {

    }

    public HotelEntity(Long hotelId, String name, String phoneNumber, int starRatting, AddressEntity address) {
        this.hotelId = hotelId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.starRatting = starRatting;
        this.address = address;
    }
}
