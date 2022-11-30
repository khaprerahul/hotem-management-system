package com.guest.entity;

import javax.persistence.Embeddable;

@Embeddable
public class CardEntity {
    private String cardNumber;
    private String expMonth;
    private String expYear;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public CardEntity() {
    }

    public CardEntity(String cardNumber, String expMonth, String expYear) {
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }
}
