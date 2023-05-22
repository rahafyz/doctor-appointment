package com.blubank.doctorappointment.mapper;

import com.blubank.doctorappointment.dto.PatientAppointmentDTO;
import com.blubank.doctorappointment.model.Patient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientAppointmentDTO toDTO(Patient patient);
}
