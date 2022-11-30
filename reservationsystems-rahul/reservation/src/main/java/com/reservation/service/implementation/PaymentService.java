package com.reservation.service.implementation;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.reservation.mapper.IMapper;
import com.reservation.message.PaymentStream;
import com.reservation.message.model.PaymentPayload;
import com.reservation.model.ICard;
import com.reservation.proxy.IPaymentServiceProxy;
import com.reservation.service.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class PaymentService implements IPaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private IPaymentServiceProxy paymentServiceProxy;

    @Inject
    private IMapper mapper;

    private PaymentStream paymentStream;

    public PaymentService(PaymentStream paymentStream){
        this.paymentStream = paymentStream;
    }

    public void sendPaymentRequest(PaymentPayload paymentPayload){
        LOGGER.debug("Sending payment request {} ", paymentPayload);
        MessageChannel messageChannel = this.paymentStream.sendPaymentSteam();
        messageChannel.send(MessageBuilder.withPayload(paymentPayload)
                .setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build());
    }
    @Override
    @HystrixCommand(fallbackMethod = "doPaymentFallback",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
        }
    )
    public String doPayment(ICard card, double amount, Long reservationId) {
        PaymentPayload paymentPayload = new PaymentPayload(reservationId, amount, card) ;
        sendPaymentRequest(paymentPayload);
        return paymentServiceProxy.doPayment(mapper.mapICardToProxy(card), amount);
    }

    @Override
    public String doPaymentFallback(ICard card, double amount, Long reservationId) {
        LOGGER.error("Payment Service is down while handling payment over card details: {}", card);
        return "SUCCESS";
    }
}
