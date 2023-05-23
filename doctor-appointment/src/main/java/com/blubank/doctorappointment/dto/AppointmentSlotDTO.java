package com.blubank.doctorappointment.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AppointmentSlotDTO {
    @JsonIgnore
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
