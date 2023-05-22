package com.blubank.doctorappointment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
