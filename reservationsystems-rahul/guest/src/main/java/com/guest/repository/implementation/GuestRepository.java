package com.guest.repository.implementation;

import com.guest.entity.GuestEntity;
import com.guest.repository.IGuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
public class GuestRepository {

    @Autowired
    private IGuestRepository repository;
    public GuestEntity save(GuestEntity guestEntity){
        return repository.save(guestEntity);
    }

    public GuestEntity findById(Long id) throws EntityNotFoundException {
        Optional<GuestEntity> guestOption = repository.findById(id);
        return guestOption.isPresent() ? guestOption.get() : guestOption.orElseThrow(()-> new EntityNotFoundException("Guest not found."));
    }
}
