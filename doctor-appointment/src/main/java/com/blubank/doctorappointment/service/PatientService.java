package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.CreatePatientDTO;
import com.blubank.doctorappointment.dto.PatientDTO;
import com.blubank.doctorappointment.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    Optional<Patient> get(String phoneNumber);
    List<PatientDTO.AppointmentDTO> getAppointments(String phoneNumber);

    PatientDTO create(CreatePatientDTO patientDTO);

    PatientDTO update(Long id, PatientDTO patientDTO);
}
