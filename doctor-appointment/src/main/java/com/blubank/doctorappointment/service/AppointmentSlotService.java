package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.AppointmentSlotDTO;
import com.blubank.doctorappointment.dto.TimeDTO;
import com.blubank.doctorappointment.model.AppointmentSlot;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentSlotService {

    AppointmentSlot getById(Long id);
    List<AppointmentSlot> save(TimeDTO dto);

    List<AppointmentSlotDTO> getOpenAppointments();

    void delete(Long id);

    List<AppointmentSlotDTO> getByDate(LocalDate date);

}
