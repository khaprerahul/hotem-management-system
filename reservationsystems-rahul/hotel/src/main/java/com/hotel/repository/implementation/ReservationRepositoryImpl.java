package com.hotel.repository.implementation;

import com.hotel.entity.ReservationEntity;
import com.hotel.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
public class ReservationRepositoryImpl {

    @Autowired
    private IReservationRepository reservationRepository;

    public ReservationEntity findReservationById(Long id){
        Optional<ReservationEntity> reservationDTO = reservationRepository.findById(id);

        return reservationDTO.isPresent()?reservationDTO.get(): reservationDTO.orElseThrow(() -> new EntityNotFoundException("Reservation not found :"+id));

    }

    public ReservationEntity saveReservation(ReservationEntity reservation){

       return reservationRepository.save(reservation);
    }

}
