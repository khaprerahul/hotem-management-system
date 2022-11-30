package com.reservation.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PaymentStream {

    @Output("payment-out")
    MessageChannel sendPaymentSteam();
}
