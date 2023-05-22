package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.dto.PatientAppointmentDTO;
import com.blubank.doctorappointment.exception.CustomException;
import com.blubank.doctorappointment.mapper.PatientMapper;
import com.blubank.doctorappointment.model.Patient;
import com.blubank.doctorappointment.repository.PatientRepository;
import com.blubank.doctorappointment.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;
    private final PatientMapper mapper;

    //exceptions in controller

    @Override
    public Optional<Patient> get(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public PatientAppointmentDTO getAppointments(String phoneNumber) {
        Patient patient = get(phoneNumber).orElseThrow(
                ()-> new CustomException("there is no patient by this phone number", HttpStatus.NOT_FOUND)
        );
        return mapper.toDTO(patient);
    }
}
