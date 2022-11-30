package com.hotel.mapper;

import com.hotel.entity.HotelEntity;
import com.hotel.entity.ReservationEntity;
import com.hotel.model.IHotel;
import com.hotel.model.IReservation;

public interface IMapper {
    HotelEntity mapIHotelToHotelDTO(IHotel hotel);

    IHotel mapHotelDTOToIHotel(HotelEntity hotelEntity);

    IReservation mapReservationDTOToIReservation(ReservationEntity reservationEntity);

    ReservationEntity mapIReservationToReservationDTO(IReservation iReservation);
}
