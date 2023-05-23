package com.blubank.doctorappointment.exception;

import org.springframework.http.HttpStatus;

public class ReservedAppointmentSlotException extends CustomException{
    public ReservedAppointmentSlotException() {
        super("this appointment is taken", HttpStatus.NOT_ACCEPTABLE);
    }
}
