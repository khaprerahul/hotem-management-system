package com.example.paymentservice.service.implementation;

import com.example.paymentservice.message.PaymentStream;
import com.example.paymentservice.message.model.PaymentPayload;
import com.example.paymentservice.model.ICard;
import com.example.paymentservice.service.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;

public class PaymentService implements IPaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    public String doPayment(ICard card, double amount){
        return "SUCCESS";
    }

    @Override
    public String revertPayment(ICard card, double amount) {
        log.debug("Received request to revert payment.");
        return "SUCCESS";
    }

    @StreamListener(PaymentStream.input)
    @Override
    public void receiveMessage(PaymentPayload paymentPayload) {
      log.debug("Successful received message from kafka : {}", paymentPayload);
    }

}
