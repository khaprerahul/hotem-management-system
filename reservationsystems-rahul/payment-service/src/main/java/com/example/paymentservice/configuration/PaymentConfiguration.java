package com.example.paymentservice.configuration;

import com.example.paymentservice.service.IPaymentService;
import com.example.paymentservice.service.implementation.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    @Bean
    public IPaymentService getPaymentService(){
        return new PaymentService();
    }

}
