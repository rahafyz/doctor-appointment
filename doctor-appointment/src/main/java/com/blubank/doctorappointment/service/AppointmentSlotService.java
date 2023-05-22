package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.TimeDTO;
import com.blubank.doctorappointment.model.AppointmentSlot;

import java.util.List;

public interface AppointmentSlotService {

    List<AppointmentSlot> save(TimeDTO dto);
}
