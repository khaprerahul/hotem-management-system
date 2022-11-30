package com.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reservation.exception.ReservationEntityNotFoundException;
import com.reservation.model.IGuest;
import com.reservation.model.IReservation;
import com.reservation.model.implementation.Guest;
import com.reservation.model.implementation.Reservation;
import com.reservation.model.implementation.ReservationStatus;
import com.reservation.proxy.model.payment.ICard;
import com.reservation.proxy.model.payment.implementation.Card;
import com.reservation.service.IReservationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private IReservationService reservationService;

    private ICard card =  new Card("1234567890","12","2024");
    private IReservation reservation = new Reservation(new Date(), new Date(), 1L, 1L, 1L, ReservationStatus.REQUEST,"SINGLE", new com.reservation.model.implementation.Card(card.getCardNumber(), card.getExpMonth(), card.getExpYear()));
    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void reservationRequest() throws Exception {
        ObjectMapper mapper =  new ObjectMapper();
        String json =  mapper.writeValueAsString(reservation);
        given(reservationService.requestForReservation(any())).willReturn(reservation);
        mockMvc.perform(post("/reservations")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .with(user("Guest")
                            .password("password")
                            .roles("GUEST")))
                .andExpect(status().isOk());
    }

    @Test
    public void confirmReservation() throws Exception, ReservationEntityNotFoundException {
        ObjectMapper mapper =  new ObjectMapper();
        String json =  mapper.writeValueAsString(reservation);
        given(reservationService.updateReservation(any())).willReturn(reservation);
        mockMvc.perform(patch("/reservations")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());

    }

    /*@Test
    public void doPayment() throws Exception {
        ObjectMapper mapper =  new ObjectMapper();
        String json =  mapper.writeValueAsString(card);
        given(reservationService.doPayment(any(), anyDouble(), anyLong())).willReturn("SUCCESS");
        mockMvc.perform(post("/reservations/payment")
                .content(json)
                .param("amount","500.0")
                .param("reservationId","1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());

    }*/

    @Test
    public void getReservation() throws Exception, ReservationEntityNotFoundException {
        given(reservationService.getReservation(anyLong(), anyBoolean())).willReturn(reservation);
        mockMvc.perform(get("/reservations/{id}","1")
                .param("isDetailsRequired","false")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());
    }

    @Test
    public void cancelReservation() throws Exception, ReservationEntityNotFoundException {
        reservation.setState(ReservationStatus.CANCELLED);
        ObjectMapper mapper =  new ObjectMapper();
        String input =  mapper.writeValueAsString(reservation);
        given(reservationService.updateReservation(any())).willReturn(reservation);
        mockMvc.perform(patch("/reservations")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(input)
                        .with(user("Guest")
                                .password("password")
                                .roles("GUEST"))
                        )
                .andExpect(status().isOk());

    }

    @Test
    public void getReservationsByGuestId() throws Exception, ReservationEntityNotFoundException {
        IGuest guest =  new Guest(1l, "Jayant","jayant@gmail.com", "123456789", 3, new ArrayList<>());
        given(reservationService.getReservationsByGuestId(anyLong())).willReturn(guest);

        mockMvc.perform(get("/reservations/guests/{guestId}","1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST"))
        )
                .andExpect(status().isOk());

    }
}