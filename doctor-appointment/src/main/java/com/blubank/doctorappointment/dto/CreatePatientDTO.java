package com.blubank.doctorappointment.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreatePatientDTO {

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    private Long appointmentSlotId;
}
