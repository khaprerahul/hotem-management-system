package com.example.paymentservice.message.model;

import com.example.paymentservice.model.ICard;
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
