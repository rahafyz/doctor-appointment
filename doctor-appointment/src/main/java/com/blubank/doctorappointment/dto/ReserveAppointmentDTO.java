package com.blubank.doctorappointment.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReserveAppointmentDTO {

    @NotNull
    private CreatePatientDTO patientDTO;

    @NotNull
    private Long appointmentSlotId;

}
