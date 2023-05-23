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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    private static final Pageable pageable = PageRequest.of(0, 1);

    @BeforeEach
    void init() {
        service = new AppointmentServiceImpl(repository, mapper);
    }

    @Test
    void findAll_shouldReturnAppointmentDTOList(){
        when(mapper.toDTOList(repository.findAll(pageable).getContent())).thenReturn(List.of(appointmentDTO()));

        Assertions.assertEquals(List.of(appointmentDTO()),service.findAll(pageable));

        Assertions.assertArrayEquals(List.of(appointmentDTO()).toArray(), service.findAll(pageable).toArray());

        Assertions.assertEquals(List.of(appointmentDTO()).size(), service.findAll(pageable).size());
    }

    @Test
    void findAll_whenNoAppointment_shouldReturnEmptyList(){
        when(mapper.toDTOList(repository.findAll())).thenReturn(Collections.emptyList());

        Assertions.assertEquals(Collections.EMPTY_LIST, service.findAll(pageable));
    }
}
