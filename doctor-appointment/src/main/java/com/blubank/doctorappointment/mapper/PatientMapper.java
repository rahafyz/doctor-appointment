package com.blubank.doctorappointment.mapper;

import com.blubank.doctorappointment.dto.CreatePatientDTO;
import com.blubank.doctorappointment.dto.PatientDTO;
import com.blubank.doctorappointment.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper extends EntityMapper<Patient, PatientDTO> {
    Patient toEntity(CreatePatientDTO dto);
}
