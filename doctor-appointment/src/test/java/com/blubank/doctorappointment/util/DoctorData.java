package com.blubank.doctorappointment.util;

import com.blubank.doctorappointment.model.Doctor;

public class DoctorData {

    private static final String NAME = "name";
    private static final String PHONE_NUMBER = "09283946635";
    private static final Long ID = 1L;

    public static Doctor doctor() {
        return Doctor.builder().id(ID).name(NAME).phoneNumber(PHONE_NUMBER).build();
    }
}
