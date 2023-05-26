package com.blubank.doctorappointment.util;

import com.blubank.doctorappointment.dto.CreatePatientDTO;
import com.blubank.doctorappointment.dto.PatientDTO;
import com.blubank.doctorappointment.model.Patient;

import java.util.List;

import static com.blubank.doctorappointment.util.AppointmentData.appointment;
import static com.blubank.doctorappointment.util.AppointmentSlotData.appointmentSlotDTO;

public class PatientData {
    public static final String NAME = "name";
    public static final String PHONE_NUMBER = "09121234567";

    public static Patient patient() {
        return Patient.builder()
                .id(1L)
                .name(NAME)
                .phoneNumber(PHONE_NUMBER)
                .appointmentList(List.of(appointment())).build();
    }

    public static PatientDTO patientDTO() {
        return PatientDTO.builder()
                .id(1L)
                .name(NAME)
                .phoneNumber(PHONE_NUMBER)
                .appointmentList(List.of(PatientDTO.AppointmentDTO.builder().appointmentSlot(appointmentSlotDTO()).build())).build();
    }

    public static PatientDTO patientDTOCreated() {
        return PatientDTO.builder()
                .id(1L)
                .name(NAME)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

    public static Patient patientCreated() {
        return Patient.builder()
                .id(1L)
                .name(NAME)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

    public static Patient patientCreatedWithoutId() {
        return Patient.builder()
                .name(NAME)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }

    public static CreatePatientDTO createPatientDTO() {
        return CreatePatientDTO.builder()
                .name(NAME)
                .phoneNumber(PHONE_NUMBER)
                .build();
    }


}
