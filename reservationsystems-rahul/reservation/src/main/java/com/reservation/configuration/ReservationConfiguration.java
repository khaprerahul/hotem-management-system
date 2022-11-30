package com.reservation.configuration;

import com.reservation.mapper.IMapper;
import com.reservation.mapper.implementation.Mapper;
import com.reservation.service.IPaymentService;
import com.reservation.service.IReservationService;
import com.reservation.service.implementation.PaymentService;
import com.reservation.service.implementation.ReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationConfiguration {

    @Bean
    public IReservationService getReservationService(){
        return new ReservationService();
    }

    @Bean
    public IMapper getMapper(){
        return new Mapper();
    }
}
