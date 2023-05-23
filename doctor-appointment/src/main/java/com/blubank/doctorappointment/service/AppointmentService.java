package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.AppointmentDTO;
import com.blubank.doctorappointment.dto.CreateAppointmentDTO;

import java.util.List;

public interface AppointmentService {

    AppointmentDTO create(CreateAppointmentDTO createAppointmentDTO);
    List<AppointmentDTO> findAll();


}
