package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.dto.CreatePatientDTO;
import com.blubank.doctorappointment.dto.PatientDTO;
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
    public List<PatientDTO.AppointmentDTO> getAppointments(String phoneNumber) {
        Patient patient = this.get(phoneNumber).orElseThrow(
                ()-> new CustomException("there is no patient by this phone number", HttpStatus.NOT_FOUND)
        );
        return mapper.toDTO(patient).getAppointmentList();
    }

    @Override
    public PatientDTO create(CreatePatientDTO patientDTO) {
        if (get(patientDTO.getPhoneNumber()).isPresent())
            throw new CustomException("there is a patient by this phone number",HttpStatus.CONFLICT);
        Patient patient = mapper.toEntity(patientDTO);
        return mapper.toDTO(repository.save(patient));
    }

    @Override
    public PatientDTO update(Long id, PatientDTO patientDTO) {
        Patient patient = repository.findById(id).orElseThrow(
                ()-> new CustomException("patient not found", HttpStatus.NOT_FOUND)
        );
        mapper.partialUpdate(patient,patientDTO);
        return mapper.toDTO(repository.save(patient));
    }
}
