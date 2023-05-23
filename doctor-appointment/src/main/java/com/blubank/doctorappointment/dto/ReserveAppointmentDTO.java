package com.blubank.doctorappointment.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReserveAppointmentDTO {

    @NotNull
    private CreatePatientDTO patientDTO;

    @NotNull
    private Long appointmentSlotId;

}
