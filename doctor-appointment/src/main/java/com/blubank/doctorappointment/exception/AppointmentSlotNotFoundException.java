package com.blubank.doctorappointment.exception;

import org.springframework.http.HttpStatus;

public class AppointmentSlotNotFoundException extends CustomException{
    public AppointmentSlotNotFoundException() {
        super("no appointment found", HttpStatus.NOT_FOUND);
    }
}
