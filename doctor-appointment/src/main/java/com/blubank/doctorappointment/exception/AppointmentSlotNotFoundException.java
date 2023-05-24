package com.blubank.doctorappointment.exception;

import org.springframework.http.HttpStatus;

public class AppointmentSlotNotFoundException extends CustomException{
    private final static String desc = "blubank.appointment.slot.not.found.exception";
    public AppointmentSlotNotFoundException() {
        super(desc, HttpStatus.NOT_FOUND);
    }
}
