package com.reservation.mapper;

import com.reservation.entity.CardEntity;
import com.reservation.entity.ReservationEntity;
import com.reservation.model.ICard;
import com.reservation.model.IGuest;
import com.reservation.model.IReservation;

public interface IMapper {

    public ReservationEntity mapIReservationToReservationDTO(IReservation reservation);

    public IReservation mapReservationDTOToIReservation(ReservationEntity reservationEntity);

    public CardEntity mapICardToCardDTO(ICard card);

    com.reservation.proxy.model.payment.ICard mapICardToProxy(ICard card);

    public ICard mapCardDTOToICard(CardEntity card);

    IGuest mapProxyGuestToIGuest(com.reservation.proxy.model.guest.IGuest body);
}
