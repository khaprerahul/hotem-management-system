package com.example.paymentservice.service;

import com.example.paymentservice.message.model.PaymentPayload;
import com.example.paymentservice.model.ICard;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.messaging.handler.annotation.Payload;

public interface IPaymentService {

    public String doPayment(ICard card, double amount);

    String revertPayment(ICard card, double amount);

    public void receiveMessage(@Payload PaymentPayload  paymentPayload);
}
