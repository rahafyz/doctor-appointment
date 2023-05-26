package com.blubank.doctorappointment.dto;

import com.blubank.doctorappointment.model.AppointmentSlot;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class CreateAppointmentDTO {
    private AppointmentSlot appointmentSlot;
    private PatientDTO patient;
}
