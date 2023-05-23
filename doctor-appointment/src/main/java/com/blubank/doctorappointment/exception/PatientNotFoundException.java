package com.blubank.doctorappointment.exception;

import org.springframework.http.HttpStatus;

public class PatientNotFoundException extends CustomException{
    public PatientNotFoundException() {
        super("there is no patient by this information", HttpStatus.NOT_FOUND);
    }
}
