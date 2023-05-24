package com.blubank.doctorappointment.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class CreateAppointmentDTO {
    private AppointmentSlotDTO appointmentSlot;
    private PatientDTO patient;
}
