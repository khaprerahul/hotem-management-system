package com.reservation.model.implementation;

import com.reservation.model.IGuest;
import com.reservation.model.IReservation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Class model to hold guest information as well as reservations.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guest implements IGuest {
    @ApiModelProperty("Unique ID for Guest.")
    private Long guestId;

    @ApiModelProperty("Name of Guest.")
    private String name;

    @ApiModelProperty("Email ID of Guest.")
    private String email;

    @ApiModelProperty("Contact number of Guest.")
    private String contactNumber;
    private int ratting;

    @ApiModelProperty("List of reservations.")
    private List<IReservation> reservations =  new ArrayList<>();

}
