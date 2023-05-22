package com.blubank.doctorappointment.mapper;

import com.blubank.doctorappointment.dto.AppointmentDTO;
import com.blubank.doctorappointment.model.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper extends EntityMapper<Appointment,AppointmentDTO>{
}
