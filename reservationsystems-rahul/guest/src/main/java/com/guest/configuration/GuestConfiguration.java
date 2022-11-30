package com.guest.configuration;

import com.guest.mapper.IMapper;
import com.guest.mapper.implementation.Mapper;
import com.guest.service.IGuestService;
import com.guest.service.implementation.GuestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuestConfiguration {
    @Bean
    public IGuestService getGuestService() {
        return new GuestService();
    }

    @Bean
    public IMapper getMapper() {
        return new Mapper();
    }
}
