package com.blubank.doctorappointment.mapper;

import com.blubank.doctorappointment.dto.AppointmentDTO;
import com.blubank.doctorappointment.model.Appointment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    AppointmentDTO toDTO(Appointment appointment);

    List<AppointmentDTO> toDTOList(List<Appointment> appointments);
}
