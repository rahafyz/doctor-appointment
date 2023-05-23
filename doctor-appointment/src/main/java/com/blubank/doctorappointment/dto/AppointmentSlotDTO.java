package com.blubank.doctorappointment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AppointmentSlotDTO {
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private Boolean isAvailable;
}
