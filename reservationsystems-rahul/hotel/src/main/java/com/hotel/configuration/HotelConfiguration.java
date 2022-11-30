package com.hotel.configuration;

import com.hotel.mapper.IMapper;
import com.hotel.mapper.implementation.Mapper;
import com.hotel.services.IHotelService;
import com.hotel.services.implementation.HotelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotelConfiguration {

    @Bean
    public IHotelService getHotelService(){
        return new HotelService();
    }

    @Bean
    public IMapper getMapperForIHotelAndHotelDTO(){
        return new Mapper();
    }

}
