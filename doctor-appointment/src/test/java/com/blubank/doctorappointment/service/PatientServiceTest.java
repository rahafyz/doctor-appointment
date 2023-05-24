package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.PatientDTO;
import com.blubank.doctorappointment.exception.CustomException;
import com.blubank.doctorappointment.exception.DuplicatePatientException;
import com.blubank.doctorappointment.exception.PatientNotFoundException;
import com.blubank.doctorappointment.mapper.PatientMapper;
import com.blubank.doctorappointment.mapper.PatientMapperImpl;
import com.blubank.doctorappointment.repository.PatientRepository;
import com.blubank.doctorappointment.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.blubank.doctorappointment.util.PatientData.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository repository;

    @Spy
    private PatientMapperImpl mapper;

    private PatientService service;

    private static final Long ID = 1L;

    @BeforeEach
    void init() {
        service = new PatientServiceImpl(repository, mapper);
    }

    @Test
    void get_shouldReturnOptionalOfPatient() {
        when(repository.findByPhoneNumber(PHONE_NUMBER)).thenReturn(Optional.ofNullable(patient()));

        Assertions.assertEquals(Optional.ofNullable(patient()), service.get(PHONE_NUMBER));

    }

    @Test
    void getAppointments_shouldReturnAppointmentDTOList() {
        when(repository.findByPhoneNumber(PHONE_NUMBER)).thenReturn(Optional.ofNullable(patient()));

        List<PatientDTO.AppointmentDTO> appointments = service.getAppointments(PHONE_NUMBER);

        Assertions.assertEquals(patientDTO().getAppointmentList(), appointments);

        Assertions.assertArrayEquals(patientDTO().getAppointmentList().toArray(), appointments.toArray());

        Assertions.assertEquals(patientDTO().getAppointmentList().size(), appointments.size());
    }

    @Test
    void getAppointments_whenPatientNotFound_shouldThrowException() {
        when(repository.findByPhoneNumber(PHONE_NUMBER)).thenReturn(Optional.empty());

        Assertions.assertThrows(PatientNotFoundException.class, () -> service.getAppointments(PHONE_NUMBER));
    }

    @Test
    void create_whenPatientExist_shouldThrowException(){
        when(repository.findByPhoneNumber(createPatientDTO().getPhoneNumber())).thenReturn(Optional.ofNullable(patient()));

        Assertions.assertThrows(DuplicatePatientException.class, () -> service.create(createPatientDTO()));

    }

    @Test
    void create_shouldReturnPatientDTO(){
        when(repository.save(patient())).thenReturn(patient());

        Assertions.assertEquals(patientDTO(), service.create(createPatientDTO()));
        Assertions.assertNotNull(patientDTO().getId());

    }

    @Test
    void update_whenPatientNotExist_shouldThrowException(){
        when(repository.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomException.class, () -> service.update(ID,patientDTO()));
    }
}
