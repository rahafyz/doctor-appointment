package com.blubank.doctorappointment.exception;

import org.springframework.http.HttpStatus;

public class InvalidTimeException extends CustomException{
    private final static String desc = "blubank.appointment.slot.invalid.time.exception";
    public InvalidTimeException() {
        super("invalid time", HttpStatus.BAD_REQUEST);
    }
}
