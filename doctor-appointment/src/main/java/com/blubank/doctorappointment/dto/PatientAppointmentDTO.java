package com.blubank.doctorappointment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PatientAppointmentDTO {
    private List<PatientAppointmentDTO.AppointmentDTO> appointmentList;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AppointmentDTO{
        private AppointmentSlotDTO appointmentSlot;
    }
}
