package com.blubank.doctorappointment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointmentSlotDTO {
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime endTime;

    @AssertTrue
    public boolean isValid(){
        return startTime.isBefore(endTime);
    }
}
