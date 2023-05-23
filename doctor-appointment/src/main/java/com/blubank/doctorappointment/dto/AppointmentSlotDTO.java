package com.blubank.doctorappointment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentSlotDTO {
    @JsonIgnore
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
