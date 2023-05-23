package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.AppointmentDTO;
import com.blubank.doctorappointment.dto.CreateAppointmentDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppointmentService {

    AppointmentDTO create(CreateAppointmentDTO createAppointmentDTO);
    List<AppointmentDTO> findAll(Pageable pageable);


}
