package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.dto.CreatePatientDTO;
import com.blubank.doctorappointment.dto.PatientDTO;
import com.blubank.doctorappointment.exception.DuplicatePatientException;
import com.blubank.doctorappointment.exception.PatientNotFoundException;
import com.blubank.doctorappointment.mapper.PatientMapper;
import com.blubank.doctorappointment.model.Patient;
import com.blubank.doctorappointment.repository.PatientRepository;
import com.blubank.doctorappointment.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;
    private final PatientMapper mapper;

    //exceptions in controller

    @Override
    @Transactional(readOnly = true)
    public Optional<Patient> get(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<PatientDTO.AppointmentDTO> getAppointments(String phoneNumber) {
        Patient patient = this.get(phoneNumber).orElseThrow(
                PatientNotFoundException::new
        );
        return mapper.toDTO(patient).getAppointmentList();
    }

    @Override
    @Transactional
    public PatientDTO create(CreatePatientDTO patientDTO) {
        if (get(patientDTO.getPhoneNumber()).isPresent())
            throw new DuplicatePatientException();
        Patient patient = mapper.toEntity(patientDTO);
        return mapper.toDTO(repository.save(patient));
    }

    @Override
    @Transactional
    public PatientDTO update(Long id, PatientDTO patientDTO) {
        Patient patient = repository.findById(id).orElseThrow(
                PatientNotFoundException::new
        );
        mapper.partialUpdate(patient, patientDTO);
        return mapper.toDTO(repository.save(patient));
    }
}
