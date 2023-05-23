package com.blubank.doctorappointment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class CreatePatientDTO {

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

}
