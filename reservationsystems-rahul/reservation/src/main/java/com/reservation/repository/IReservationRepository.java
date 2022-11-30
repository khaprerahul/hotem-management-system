package com.reservation.repository;

import com.reservation.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends CrudRepository<ReservationEntity, Long> {
}
