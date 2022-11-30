package com.reservation.message.model;

import com.reservation.model.ICard;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentPayload {
    private Long guestId;
    private double amount;
    private ICard card;
}
