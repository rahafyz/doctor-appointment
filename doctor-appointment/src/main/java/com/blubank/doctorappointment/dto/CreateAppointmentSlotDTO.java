package com.blubank.doctorappointment.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAppointmentSlotDTO {
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;

    @AssertTrue
    public boolean isValid(){
        return startTime.isBefore(endTime);
    }
}
