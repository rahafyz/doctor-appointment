package com.blubank.doctorappointment.exception;

import org.springframework.http.HttpStatus;

public class InvalidTimeException extends CustomException{
    public InvalidTimeException() {
        super("invalid time", HttpStatus.BAD_REQUEST);
    }
}
