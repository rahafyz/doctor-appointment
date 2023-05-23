package com.blubank.doctorappointment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class PatientDTO {

    @JsonIgnore
    private Long id;

    private String name;

    private String phoneNumber;

    private List<PatientDTO.AppointmentDTO> appointmentList;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AppointmentDTO{
        private AppointmentSlotDTO appointmentSlot;
    }

}
