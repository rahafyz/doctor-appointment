package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.dto.AppointmentDTO;
import com.blubank.doctorappointment.mapper.AppointmentMapper;
import com.blubank.doctorappointment.repository.AppointmentRepository;
import com.blubank.doctorappointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;

    @Override
    public List<AppointmentDTO> getAll() {
        return mapper.toDTOList(repository.findAll());
    }
}
