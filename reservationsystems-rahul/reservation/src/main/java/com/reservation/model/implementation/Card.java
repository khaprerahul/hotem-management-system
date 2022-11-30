package com.reservation.model.implementation;

import com.reservation.model.ICard;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Card implements ICard {
    private String cardNumber;
    private String expMonth;
    private String expYear;

}
