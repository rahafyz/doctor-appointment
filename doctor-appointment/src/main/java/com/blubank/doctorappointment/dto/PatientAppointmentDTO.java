package com.blubank.doctorappointment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PatientAppointmentDTO {
    private List<PatientAppointmentDTO.AppointmentDTO> appointmentList;

    @Getter
    @Setter
    private static class AppointmentDTO{
        private AppointmentSlotDTO appointmentSlot;
    }
}
