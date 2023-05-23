package com.blubank.doctorappointment.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AppointmentSlotDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
