package com.example.paymentservice.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PaymentStream {

    public static final String input = "payment-in";

    @Input(input)
    SubscribableChannel receivePaymentStream();

}
