package com.guest.service.implementation;

import com.guest.entity.GuestEntity;
import com.guest.mapper.IMapper;
import com.guest.model.ICard;
import com.guest.model.IGuest;
import com.guest.repository.implementation.GuestRepository;
import com.guest.service.IGuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class GuestService implements IGuestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuestService.class);

    @Autowired
    private GuestRepository repository;

    @Inject
    private IMapper mapper;

    public IGuest addNewGuest(IGuest guest) {
        LOGGER.debug("GuestService :: getGuest :: Request to add new guest.{}", guest);
        GuestEntity savedGuest = repository.save(mapper.mapIGuestToGuestDTO(guest));
        return mapper.mapGuestDTOToIGuest(savedGuest);
    }

    public IGuest getGuest(Long id) throws EntityNotFoundException {
        LOGGER.debug("GuestService :: getGuest :: Request to fetch guest with id {}",id);
        GuestEntity guest = repository.findById(id);
        IGuest iGuest = mapper.mapGuestDTOToIGuest(guest);
        LOGGER.debug("GuestService :: getGuest :: Guest information returned {} ",iGuest);
        return iGuest;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public IGuest addStayByGuest(Long guestId, Long reservationId) throws EntityNotFoundException {
        LOGGER.debug("GuestService :: addStayByGuest :: Guest requested for new stay, Guest ID {} , stay ID {}", guestId, reservationId);
        GuestEntity guest = repository.findById(guestId);
        guest.getReservations().add(reservationId);
        return mapper.mapGuestDTOToIGuest(guest);
    }

    public List<IGuest> getGuests(List<Long> guestIds ) throws EntityNotFoundException {
        List<IGuest> guests = new ArrayList<>();
        for(Long guestId : guestIds){
            IGuest guest = getGuest(guestId);
            guests.add(guest);
        }
        return guests;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public IGuest addNewCard(long guestId, ICard card) {
        GuestEntity guestEntity = repository.findById(guestId);
        guestEntity.getCards().add(mapper.mapICardToCardDTO(card));
        return mapper.mapGuestDTOToIGuest(guestEntity);
    }
}
