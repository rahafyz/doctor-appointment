package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.AppointmentDTO;
import com.blubank.doctorappointment.dto.ReserveAppointmentDTO;

public interface AppointmentFacadeService {
    AppointmentDTO reserveAppointment(ReserveAppointmentDTO reserveDTO);
}
