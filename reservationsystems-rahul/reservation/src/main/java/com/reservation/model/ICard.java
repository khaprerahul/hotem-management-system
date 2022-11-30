package com.reservation.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reservation.model.implementation.Card;


@JsonDeserialize(as = Card.class)
public interface ICard {

    public String getCardNumber();

    public void setCardNumber(String cardNumber);

    public String getExpMonth();

    public void setExpMonth(String expMonth);

    public String getExpYear();

    public void setExpYear(String expYear);
}
