package com.reservation.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Reservation")
public class ReservationEntity {
    private Date fromDate;
    private Date toDate;
    private Long guestId;
    private Long hotelId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;
    private String state;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cardId")
    private CardEntity card;

    private double amount;

    public ReservationEntity(Date fromDate, Date toDate, Long guestId, Long hotelId, Long reservationId, String state, CardEntity card) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.guestId = guestId;
        this.hotelId = hotelId;
        this.reservationId = reservationId;
        this.state = state;
        this.card = card;
    }
}
