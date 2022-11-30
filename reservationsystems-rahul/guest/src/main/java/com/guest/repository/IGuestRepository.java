package com.guest.repository;


import com.guest.entity.GuestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGuestRepository extends CrudRepository<GuestEntity, Long> {

}
