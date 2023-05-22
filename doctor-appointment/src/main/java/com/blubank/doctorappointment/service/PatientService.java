package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.PatientAppointmentDTO;
import com.blubank.doctorappointment.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    Optional<Patient> get(String phoneNumber);
    PatientAppointmentDTO getAppointments(String phoneNumber);
}
