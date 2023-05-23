package com.blubank.doctorappointment.exception;

import org.springframework.http.HttpStatus;

public class DuplicatePatientException extends CustomException{
    public DuplicatePatientException() {
        super("there is a patient by this phone number",HttpStatus.NOT_ACCEPTABLE);
    }
}
