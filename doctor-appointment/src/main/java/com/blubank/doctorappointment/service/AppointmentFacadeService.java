package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.ReserveAppointmentDTO;

public interface AppointmentFacadeService {
    void reserveAppointment(ReserveAppointmentDTO reserveDTO);
}
