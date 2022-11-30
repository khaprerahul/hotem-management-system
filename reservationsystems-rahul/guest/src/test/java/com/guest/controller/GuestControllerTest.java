package com.guest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guest.model.ICard;
import com.guest.model.IGuest;
import com.guest.model.implementation.Card;
import com.guest.model.implementation.Guest;
import com.guest.service.IGuestService;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityNotFoundException;

import java.util.Arrays;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GuestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private IGuestService guestService;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    private IGuest guest = new Guest(1L, "Jayant","jayant@gmail.com","1234567");
    @Test
    public void getGuest_ShouldReturnGuest() throws Exception {
        given(guestService.getGuest(1L)).willReturn(guest);

        mockMvc.perform(delete("/v1/room/{roomId}", "10"));

        String response = mockMvc.perform(get("/guests/{guestId}", "1")
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        DocumentContext parsedJson = JsonPath.parse(response);
        assertThatJson(parsedJson).field("['status']").isEqualTo("OK");
        assertThatJson(parsedJson).field("['actualResponse']").field("['name']").isEqualTo("Jayant");
        assertThatJson(parsedJson).field("['actualResponse']").field("['guestId']").isEqualTo("1");


    }

    @Test
    public void getGuest_EntityNotFoundException() throws Exception {
        given(guestService.getGuest(anyLong())).willThrow(new EntityNotFoundException("Entity Not found"));
        mockMvc.perform(MockMvcRequestBuilders.get("/guests/{guestId}","1")
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void addNewStay() throws Exception {
        IGuest guest = new Guest(1L, "Jayant","jayant@gmail.com","1234567");
        given(guestService.addStayByGuest(anyLong(), any())).willReturn(guest);
        mockMvc.perform(MockMvcRequestBuilders.put("/guests/{guestId}/stay","1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("reservationId","1")
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());
                //.andExpect(content().string("Stay added successfully."));
    }

    @Test
    public void addNewGuest() throws Exception {
        IGuest guest = new Guest(1L, "Jayant","jayant@gmail.com","1234567");
        given(guestService.addNewGuest(any())).willReturn(guest);
        ObjectMapper mapper =  new ObjectMapper();
        String json = mapper.writeValueAsString(guest);
        String response = mockMvc.perform(post("/guests")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        DocumentContext parsedJson = JsonPath.parse(response);
        assertThatJson(parsedJson).field("['status']").isEqualTo("CREATED");

    }

    @Test
    public void getGuests() throws Exception {
        IGuest guest = new Guest(1L, "Jayant","jayant@gmail.com","1234567");
        given(guestService.getGuests(any())).willReturn(Arrays.asList(guest));

        MvcResult mvcResult = mockMvc.perform(get("/guests")
                .param("guestId", "1")
                .param("guestId", "2")
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Jayant"));
    }


    @Test
    public void addNewCard() throws Exception {
        ICard card =  new Card("1234567890765", "12","2014");
        IGuest guest = new Guest(1L, "Jayant","jayant@gmail.com","1234567");
        guest.getCards().add(card);
        ObjectMapper mapper =  new ObjectMapper();
        String json = mapper.writeValueAsString(card);
        given(guestService.addNewCard(anyLong(), any())).willReturn(guest);
        mockMvc.perform(post("/guests/{guestId}/card","1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                        .with(user("Guest")
                                .password("password")
                                .roles("GUEST")))
                        .andExpect(status().isOk());

    }
}