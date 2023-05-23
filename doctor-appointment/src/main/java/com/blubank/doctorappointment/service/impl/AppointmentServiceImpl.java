package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.dto.AppointmentDTO;
import com.blubank.doctorappointment.dto.CreateAppointmentDTO;
import com.blubank.doctorappointment.mapper.AppointmentMapper;
import com.blubank.doctorappointment.model.Appointment;
import com.blubank.doctorappointment.repository.AppointmentRepository;
import com.blubank.doctorappointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;

    @Override
    @Transactional
    public AppointmentDTO create(CreateAppointmentDTO createAppointmentDTO) {
        Appointment appointment = mapper.toEntity(createAppointmentDTO);
        return mapper.toDTO(repository.save(appointment));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> findAll(Pageable pageable) {
        return mapper.toDTOList(repository.findAll(pageable).getContent());
    }
}
