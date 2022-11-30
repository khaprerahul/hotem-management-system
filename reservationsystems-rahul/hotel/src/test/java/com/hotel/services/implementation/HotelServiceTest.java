package com.hotel.services.implementation;


import com.hotel.entity.AddressEntity;
import com.hotel.entity.HotelEntity;
import com.hotel.entity.ReservationEntity;
import com.hotel.entity.RoomEntity;
import com.hotel.model.IAddress;
import com.hotel.model.IHotel;
import com.hotel.model.IReservation;
import com.hotel.model.implementation.*;
import com.hotel.repository.implementation.HotelRepositoryImpl;
import com.hotel.repository.implementation.ReservationRepositoryImpl;
import com.hotel.services.IHotelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceTest {

    @Autowired
    private IHotelService hotelService;

    @MockBean
    private HotelRepositoryImpl hotelRepository;

    @MockBean
    private ReservationRepositoryImpl reservationRepository;

    private IAddress address = new Address(1L, "Lane no1", "Hanuman Nagar", "Pune","412308");
    private IHotel hotel = new Hotel(1L, "City Inn", "1234567890",3, address);
    private IReservation reservation = new Reservation(new Room(),1L, new Date(), new Date(), 1L, ReservationStatus.REQUEST, "SINGLE");

    private AddressEntity addressEntity =  new AddressEntity(1L, "Lane no1", "Hanuman Nagar", "Pune","412308");
    private HotelEntity hotelEntity =  new HotelEntity(1L, "City Inn", "1234567890",3, addressEntity);
    private RoomEntity roomEntity =  new RoomEntity(102, 1000, RoomType.SINGLE);
    private ReservationEntity reservationEntity =  new ReservationEntity(new RoomEntity(),1L, new Date(), new Date(), 1L, "REQUEST", "SINGLE");
    private ReservationEntity reservationEntity2 =  new ReservationEntity(new RoomEntity(),2L, new Date(), new Date(), 1L, "REQUEST", "SINGLE");

    @Test
    public void addNewHotel() {
        given(hotelRepository.save(any())).willReturn(hotelEntity);
        IHotel iHotel = hotelService.addNewHotel(hotel);
        assertEquals(iHotel.getHotelId(), hotelEntity.getHotelId());
    }

    @Test
    public void getReservationByGuestIdPerHotel() {

        hotelEntity.getReservations().add(reservationEntity);
        hotelEntity.getReservations().add(reservationEntity2);
        given(hotelRepository.findById(anyLong())).willReturn(hotelEntity);
        List<IReservation> reservation = hotelService.getReservationByGuestIdPerHotel(1L, 1L);

        assertFalse(reservation.stream().filter(r -> !r.getGuestId().equals(1L)).findFirst().isPresent());

    }

    @Test
    public void cancelReservation() {
        reservationEntity.setState("CANCELLED");
        given(reservationRepository.findReservationById(anyLong())).willReturn(reservationEntity);
        reservation.setState(ReservationStatus.CANCELLED);
        IReservation iReservation = hotelService.updateReservation(1l, reservation);

        assertEquals(1l, iReservation.getReservationId());
        assertEquals(ReservationStatus.CANCELLED, iReservation.getState());
    }

    @Test(expected = EntityNotFoundException.class)
    public void cancelReservation_EntityNotFound() {
        given(reservationRepository.findReservationById(anyLong())).willThrow(new EntityNotFoundException("Entity Not found"));
        reservation.setState(ReservationStatus.CANCELLED);
        hotelService.updateReservation(1l, reservation);
    }

    @Test
    public void reservationRequest() {
        hotelEntity.setReservations(new ArrayList<>());
        hotelEntity.getRooms().add(roomEntity);
        given(hotelRepository.findById(anyLong())).willReturn(hotelEntity);
        IReservation iReservation = hotelService.reservationRequest(1L, reservation);

        assertEquals(ReservationStatus.REQUEST, iReservation.getState());
    }

    @Test
    public void getAllReservationsByHotelId() {
        given((hotelRepository.findById(anyLong()))).willReturn(hotelEntity);
        hotelEntity.getReservations().add(reservationEntity2);
        hotelEntity.getReservations().add(reservationEntity);
        List<IReservation> reservations = hotelService.getAllReservationsByHotelId(1L);

        assertEquals(2, reservations.size());
    }

    @Test
    public void getHotelById() {
        given(hotelRepository.findById(anyLong())).willReturn(hotelEntity);
        IHotel iHotel = hotelService.getHotelById(1L);
        assertEquals(1l, iHotel.getHotelId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void getHotelById_EntityNotFound() {
        given(hotelRepository.findById(anyLong())).willThrow(new EntityNotFoundException("Entity Not found"));
        hotelService.getHotelById(1L);
    }

    @Test
    public void searchHotels() {
        AddressEntity addressEntity1 =  new AddressEntity(2L, "Lane no1", "Hanuman Nagar", "Kolhapur","412308");
        HotelEntity hotelEntity1 =  new HotelEntity(2L, "City Inn", "1234567890",3, addressEntity1);
        given(hotelRepository.getAllHotels()).willReturn(Arrays.asList(hotelEntity, hotelEntity1));
        List<IHotel> hotels = hotelService.searchHotelsByCity("Pune");

        assertEquals(1, hotels.size());
        assertFalse(hotels.stream().filter(h -> !h.getAddress().getCity().equals("Pune")).findFirst().isPresent());

    }
}