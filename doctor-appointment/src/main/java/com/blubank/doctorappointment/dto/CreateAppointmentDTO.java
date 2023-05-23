package com.blubank.doctorappointment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateAppointmentDTO {
    AppointmentSlotDTO appointmentSlot;
    PatientDTO patient;
}
