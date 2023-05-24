package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.exception.DoctorNotFoundException;
import com.blubank.doctorappointment.model.Doctor;
import com.blubank.doctorappointment.repository.DoctorRepository;
import com.blubank.doctorappointment.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;
    @Override
    public Doctor getById(Long id) {
        return repository.findById(id).orElseThrow(DoctorNotFoundException::new);
    }
}
