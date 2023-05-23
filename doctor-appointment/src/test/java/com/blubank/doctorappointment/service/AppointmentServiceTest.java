package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.mapper.AppointmentMapper;
import com.blubank.doctorappointment.repository.AppointmentRepository;
import com.blubank.doctorappointment.service.impl.AppointmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static com.blubank.doctorappointment.util.AppointmentData.appointmentDTO;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository repository;

    @Mock
    private AppointmentMapper mapper;

    private AppointmentService service;

    @BeforeEach
    void init() {
        service = new AppointmentServiceImpl(repository, mapper);
    }

    @Test
    void findAll_shouldReturnAppointmentDTOList(){
        when(mapper.toDTOList(repository.findAll())).thenReturn(List.of(appointmentDTO()));

        Assertions.assertEquals(List.of(appointmentDTO()),service.findAll());

        Assertions.assertArrayEquals(List.of(appointmentDTO()).toArray(), service.findAll().toArray());

        Assertions.assertEquals(List.of(appointmentDTO()).size(), service.findAll().size());
    }

    @Test
    void findAll_whenNoAppointment_shouldReturnEmptyList(){
        when(mapper.toDTOList(repository.findAll())).thenReturn(Collections.emptyList());

        Assertions.assertEquals(Collections.EMPTY_LIST, service.findAll());
    }
}
