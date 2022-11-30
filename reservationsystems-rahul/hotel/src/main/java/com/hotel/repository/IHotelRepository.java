package com.hotel.repository;

import com.hotel.entity.HotelEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository extends JpaRepository<HotelEntity, Long> {
}
