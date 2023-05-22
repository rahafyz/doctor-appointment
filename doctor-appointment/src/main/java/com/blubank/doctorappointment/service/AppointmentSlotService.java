package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.AppointmentSlotDTO;
import com.blubank.doctorappointment.dto.TimeDTO;
import com.blubank.doctorappointment.model.AppointmentSlot;

import java.util.List;

public interface AppointmentSlotService {

    AppointmentSlotDTO getById(Long id);
    List<AppointmentSlot> save(TimeDTO dto);

    void delete(Long id);


}
