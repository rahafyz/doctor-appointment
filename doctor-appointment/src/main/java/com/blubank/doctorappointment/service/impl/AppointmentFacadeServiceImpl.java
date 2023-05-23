package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.dto.*;
import com.blubank.doctorappointment.exception.CustomException;
import com.blubank.doctorappointment.mapper.AppointmentSlotMapper;
import com.blubank.doctorappointment.mapper.PatientMapper;
import com.blubank.doctorappointment.model.AppointmentSlot;
import com.blubank.doctorappointment.model.Patient;
import com.blubank.doctorappointment.service.AppointmentFacadeService;
import com.blubank.doctorappointment.service.AppointmentService;
import com.blubank.doctorappointment.service.AppointmentSlotService;
import com.blubank.doctorappointment.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentFacadeServiceImpl implements AppointmentFacadeService {

    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private final AppointmentSlotService appointmentSlotService;
    private final AppointmentSlotMapper appointmentSlotMapper;
    private final AppointmentService appointmentService;

    @Override
    public AppointmentDTO reserveAppointment(ReserveAppointmentDTO reserveDTO) {
        PatientDTO patientDTO = getPatient(reserveDTO.getPatientDTO());

        AppointmentSlotDTO appointmentSlotDTO = getAppointmentSlot(reserveDTO.getAppointmentSlotId());

        AppointmentDTO appointmentDTO = appointmentService.create(CreateAppointmentDTO.builder()
                .appointmentSlot(appointmentSlotDTO)
                .patient(patientDTO).build());

        patientDTO.getAppointmentList().add(PatientDTO.AppointmentDTO.builder()
                .appointmentSlot(appointmentDTO.getAppointmentSlot()).build());

        patientService.update(patientDTO.getId(),patientDTO);

        return appointmentDTO;
    }

    private PatientDTO getPatient(CreatePatientDTO patientDTO){
        Optional<Patient> patient = patientService.get(patientDTO.getPhoneNumber());
        if (patient.isPresent())
            return patientMapper.toDTO(patient.get());
        return patientService.create(patientDTO);
    }

    private AppointmentSlotDTO getAppointmentSlot(Long appointmentSlotId){
        AppointmentSlot appointmentSlot = appointmentSlotService.getById(appointmentSlotId);
        if (Boolean.TRUE.equals(appointmentSlot.getIsAvailable()))
            return appointmentSlotMapper.toDTO(appointmentSlot);
        throw new CustomException("this appointment is taken", HttpStatus.NOT_ACCEPTABLE);
    }
}
