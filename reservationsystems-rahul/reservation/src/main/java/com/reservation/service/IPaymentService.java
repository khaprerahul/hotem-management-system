package com.reservation.service;

import com.reservation.model.ICard;

public interface IPaymentService {
    public String doPayment(ICard card, double amount, Long reservationId);
    public String doPaymentFallback(ICard card, double amount, Long reservationId);
}
