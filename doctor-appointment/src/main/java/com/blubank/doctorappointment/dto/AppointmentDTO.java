package com.blubank.doctorappointment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDTO {
    private AppointmentSlotDTO appointmentSlot;
    private AppointmentDTO.PatientDTO patient;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PatientDTO{
        private String name;
        private String phoneNumber;
    }
}
