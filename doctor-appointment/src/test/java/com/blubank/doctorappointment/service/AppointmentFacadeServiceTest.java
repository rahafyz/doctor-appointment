package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.dto.AppointmentDTO;
import com.blubank.doctorappointment.dto.AppointmentSlotDTO;
import com.blubank.doctorappointment.dto.CreateAppointmentDTO;
import com.blubank.doctorappointment.exception.ConcurrentRequestException;
import com.blubank.doctorappointment.exception.ReservedAppointmentSlotException;
import com.blubank.doctorappointment.mapper.AppointmentSlotMapperImpl;
import com.blubank.doctorappointment.mapper.PatientMapperImpl;
import com.blubank.doctorappointment.service.impl.AppointmentFacadeServiceImpl;
import com.blubank.doctorappointment.util.LockUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RLock;

import java.util.Optional;

import static com.blubank.doctorappointment.util.AppointmentData.appointmentDTO;
import static com.blubank.doctorappointment.util.AppointmentData.createAppointmentDTO;
import static com.blubank.doctorappointment.util.AppointmentFacadeData.reserveAppointmentDTO;
import static com.blubank.doctorappointment.util.AppointmentSlotData.*;
import static com.blubank.doctorappointment.util.PatientData.patient;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentFacadeServiceTest {

    @Mock
    private PatientService patientService;

    @Spy
    private PatientMapperImpl patientMapper;

    @Mock
    private AppointmentSlotService appointmentSlotService;

    @Spy
    private AppointmentSlotMapperImpl appointmentSlotMapper;

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private LockUtil lockUtil;

    private AppointmentFacadeService facadeService;

    @BeforeEach
    void init() {
        facadeService = new AppointmentFacadeServiceImpl(patientService,patientMapper,
                appointmentSlotService,appointmentSlotMapper,appointmentService,lockUtil);
    }

    @Test
    void reserve(){
        AppointmentSlotDTO appointmentSlotDTO = appointmentSlotDTO();
        CreateAppointmentDTO createAppointmentDTO = createAppointmentDTO();
        AppointmentDTO appointmentDTO = appointmentDTO();


        RLock mockLock = mock(RLock.class);
        when(lockUtil.getLockForAppointmentSlot(1L)).thenReturn(mockLock);


        when(patientService.get(reserveAppointmentDTO().getPatientDTO().getPhoneNumber())).thenReturn(Optional.of(patient()));

        when(appointmentSlotService.getById(reserveAppointmentDTO().getAppointmentSlotId())).thenReturn(appointmentSlot());

        appointmentSlotDTO.setIsAvailable(false);
        createAppointmentDTO.setAppointmentSlot(appointmentSlotDTO);


        when(appointmentService.create(createAppointmentDTO)).thenReturn(appointmentDTO);

        facadeService.reserveAppointment(reserveAppointmentDTO());

        verify(patientService).get(reserveAppointmentDTO().getPatientDTO().getPhoneNumber());
        verify(appointmentSlotService).getById(reserveAppointmentDTO().getAppointmentSlotId());

    }

    @Test
    void reserve_whenLockIsTaken_shouldThrowException(){
        when(lockUtil.getLockForAppointmentSlot(1L)).thenReturn(null);

        Assertions.assertThrows(ConcurrentRequestException.class, () -> facadeService.reserveAppointment(reserveAppointmentDTO()));
    }

    @Test
    void reserve_whenAppointmentIsTaken_shouldThrowException(){
        RLock mockLock = mock(RLock.class);
        when(lockUtil.getLockForAppointmentSlot(1L)).thenReturn(mockLock);


        when(patientService.get(reserveAppointmentDTO().getPatientDTO().getPhoneNumber())).thenReturn(Optional.of(patient()));

        when(appointmentSlotService.getById(reserveAppointmentDTO().getAppointmentSlotId())).thenReturn(takenAppointmentSlot());

        Assertions.assertThrows(ReservedAppointmentSlotException.class, () -> facadeService.reserveAppointment(reserveAppointmentDTO()));

    }
}
