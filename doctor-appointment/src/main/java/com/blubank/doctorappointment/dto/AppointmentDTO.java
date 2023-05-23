package com.blubank.doctorappointment.dto;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AppointmentDTO {
    private AppointmentSlotDTO appointmentSlot;
    private AppointmentDTO.PatientDTO patient;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Builder
    public static class PatientDTO{
        private String name;
        private String phoneNumber;
    }
}
