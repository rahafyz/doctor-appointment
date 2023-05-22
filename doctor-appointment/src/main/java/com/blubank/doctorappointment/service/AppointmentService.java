package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDTO> findAll();
}
