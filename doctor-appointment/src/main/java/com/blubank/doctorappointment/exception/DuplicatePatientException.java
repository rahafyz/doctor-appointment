package com.blubank.doctorappointment.exception;

import org.springframework.http.HttpStatus;

public class DuplicatePatientException extends CustomException{
    private final static String desc = "blubank.patient.duplicate.exception";
    public DuplicatePatientException() {
        super(desc,HttpStatus.NOT_ACCEPTABLE);
    }
}
