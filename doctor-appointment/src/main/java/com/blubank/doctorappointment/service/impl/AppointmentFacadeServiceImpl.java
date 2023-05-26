package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.dto.*;
import com.blubank.doctorappointment.exception.ConcurrentRequestException;
import com.blubank.doctorappointment.exception.ReservedAppointmentSlotException;
import com.blubank.doctorappointment.mapper.AppointmentSlotMapper;
import com.blubank.doctorappointment.mapper.PatientMapper;
import com.blubank.doctorappointment.model.AppointmentSlot;
import com.blubank.doctorappointment.model.Patient;
import com.blubank.doctorappointment.service.AppointmentFacadeService;
import com.blubank.doctorappointment.service.AppointmentService;
import com.blubank.doctorappointment.service.AppointmentSlotService;
import com.blubank.doctorappointment.service.PatientService;
import com.blubank.doctorappointment.util.LockUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentFacadeServiceImpl implements AppointmentFacadeService {

    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private final AppointmentSlotService appointmentSlotService;
    private final AppointmentSlotMapper appointmentSlotMapper;
    private final AppointmentService appointmentService;
    private final LockUtil lockUtil;

    @Override
    @Transactional
    public void reserveAppointment(ReserveAppointmentDTO reserveDTO) {
        RLock lock = lockUtil.getLockForAppointmentSlot(reserveDTO.getAppointmentSlotId());
        if (lock != null) {
            try {
                PatientDTO patientDTO = createPatient(reserveDTO.getPatientDTO());

                AppointmentSlot appointmentSlot = getAppointmentSlot(reserveDTO.getAppointmentSlotId());

                appointmentSlot.setIsAvailable(false);

                appointmentService.create(CreateAppointmentDTO.builder()
                        .appointmentSlot(appointmentSlot)
                        .patient(patientDTO).build());

            } finally {
                lockUtil.releaseLockForAppointmentSlotTimeSlot(reserveDTO.getAppointmentSlotId());
            }
        } else {
            throw new ConcurrentRequestException();
        }
    }

    private PatientDTO createPatient(CreatePatientDTO patientDTO) {
        Optional<Patient> patient = patientService.get(patientDTO.getPhoneNumber());
        if (patient.isPresent())
            return patientMapper.toDTO(patient.get());
        return patientService.create(patientDTO);
    }

    private AppointmentSlot getAppointmentSlot(Long appointmentSlotId) {
        AppointmentSlot appointmentSlot = appointmentSlotService.getById(appointmentSlotId);
        if (Boolean.TRUE.equals(appointmentSlot.getIsAvailable()))
            return appointmentSlot;
        throw new ReservedAppointmentSlotException();
    }
}