package com.example.paymentservice.model;

import com.example.paymentservice.model.implementation.Card;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Card.class)
public interface ICard {

    public String getCardNumber();

    public void setCardNumber(String cardNumber);

    public String getExpMonth();

    public void setExpMonth(String expMonth);

    public String getExpYear();

    public void setExpYear(String expYear);
}
