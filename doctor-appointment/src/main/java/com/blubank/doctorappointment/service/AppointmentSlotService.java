package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.AppointmentSlotDTO;
import com.blubank.doctorappointment.dto.CreateAppointmentSlotDTO;
import com.blubank.doctorappointment.model.AppointmentSlot;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentSlotService {

    AppointmentSlot getById(Long id);
    List<AppointmentSlotDTO> save(CreateAppointmentSlotDTO dto);

    List<AppointmentSlotDTO> getOpenAppointments(Long doctorId);

    void delete(Long id);

    List<AppointmentSlotDTO> getByDate(LocalDate date);

}
