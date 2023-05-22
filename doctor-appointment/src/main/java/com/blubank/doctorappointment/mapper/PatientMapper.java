package com.blubank.doctorappointment.mapper;

import com.blubank.doctorappointment.dto.PatientAppointmentDTO;
import com.blubank.doctorappointment.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper extends EntityMapper<Patient, PatientAppointmentDTO> {
}
