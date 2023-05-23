package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.exception.CustomException;
import com.blubank.doctorappointment.mapper.AppointmentSlotMapper;
import com.blubank.doctorappointment.model.AppointmentSlot;
import com.blubank.doctorappointment.repository.AppointmentSlotRepository;
import com.blubank.doctorappointment.service.impl.AppointmentSlotServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.blubank.doctorappointment.util.AppointmentSlotData.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentSlotServiceTest {

    @Mock
    AppointmentSlotRepository repository;
    @Mock
    AppointmentSlotMapper mapper;

    AppointmentSlotService service;
    private static final Long ID = 1L;

    @BeforeEach
    void init() {
        service = new AppointmentSlotServiceImpl(repository, mapper);
        ReflectionTestUtils.setField(service, "timeInterval", 30);
    }

    @Test
    void getById_shouldReturnAppointmentSlot() {

        when(repository.findById(ID)).thenReturn(Optional.of(appointmentSlot()));

        Assertions.assertEquals(appointmentSlot(), service.getById(ID));
        Assertions.assertNotNull(appointmentSlot().getId());
        Assertions.assertEquals(appointmentSlot().getStartTime(), service.getById(ID).getStartTime());
        Assertions.assertEquals(appointmentSlot().getEndTime(), service.getById(ID).getEndTime());
        Assertions.assertEquals(appointmentSlot().getIsAvailable(), service.getById(ID).getIsAvailable());
    }

    @Test
    void getById_whenNotFound_shouldThrowException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomException.class, () -> {
            service.getById(ID);
        });
    }

    @Test
    void save_shouldReturnAppointmentSlotDTOList(){
        List<AppointmentSlot> appointmentSlotList = List.of(appointmentSlot());

        when(mapper.toDTOList(repository.saveAll(appointmentSlotList))).thenReturn(appointmentSlotDTOList());

        Assertions.assertEquals(appointmentSlotDTOList(), service.save(createAppointmentSlotDTO()));
        Assertions.assertNotNull(appointmentSlotList.get(0).getId());

        verify(repository, times(1)).saveAll(appointmentSlotList);
    }

    @Test
    void save_whenInvalidTime_shouldThrowException(){
        Assertions.assertThrows(CustomException.class, () -> {
            service.save(createInvalidAppointmentSlotDTO());
        });
    }

    @Test
    void save_whenShortTime_shouldAddNothing(){

        Assertions.assertEquals(Collections.emptyList(), service.save(createShortAppointmentSlotDTO()));

        verify(repository, times(0)).saveAll(List.of(appointmentSlot()));
    }

    @Test
    void getOpenAppointments_shouldReturnAppointmentSlotDTOList(){
        repository.saveAll(List.of(appointmentSlot(),takenAppointmentSlot()));
        when(mapper.toDTOList(repository.findByDoctor_IdAndIsAvailableTrue(ID))).thenReturn(appointmentSlotDTOList());

        Assertions.assertEquals(appointmentSlotDTOList(),service.getOpenAppointments(ID));

        Assertions.assertArrayEquals(appointmentSlotDTOList().toArray(), service.getOpenAppointments(ID).toArray());

        Assertions.assertEquals(appointmentSlotDTOList().size(), service.getOpenAppointments(ID).size());

    }

    @Test
    void getOpenAppointments_whenNoOpenAppointment_shouldReturnEmptyList(){
        when(mapper.toDTOList(repository.findByDoctor_IdAndIsAvailableTrue(ID))).thenReturn(Collections.EMPTY_LIST);

        Assertions.assertEquals(Collections.EMPTY_LIST, service.getOpenAppointments(ID));

    }

    /*@Test
    void delete_shouldDeleteAppointment(){
        when(repository.findById(ID)).thenReturn(Optional.ofNullable(appointmentSlot()));

        verify(repository, times(1)).deleteById(ID);

    }*/

    @Test
    void delete_whenAppointmentNotFound_shouldThrowException(){
        when(repository.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomException.class, () -> {
            service.delete(ID);
        });
    }
    @Test
    void delete_whenAppointmentIsTaken_shouldThrowException(){
        when(repository.findById(ID)).thenReturn(Optional.ofNullable(takenAppointmentSlot()));

        Assertions.assertThrows(CustomException.class, () -> {
            service.delete(ID);
        });
    }

    @Test
    void getByDate_shouldReturnAppointmentSlotDTOList(){
        when(mapper.toDTOList(repository.findByStartTimeBetween(DATE.atStartOfDay(),DATE.atTime(23, 59))))
                .thenReturn(appointmentSlotDTOList());

        Assertions.assertEquals(appointmentSlotDTOList(),service.getByDate(LocalDate.now()));

        Assertions.assertArrayEquals(appointmentSlotDTOList().toArray(), service.getByDate(LocalDate.now()).toArray());

        Assertions.assertEquals(appointmentSlotDTOList().size(), service.getByDate(LocalDate.now()).size());
    }
    @Test
    void getByDate_whenNoOpenAppointment_shouldReturnEmptyList(){
        when(mapper.toDTOList(repository.findByStartTimeBetween(DATE.atStartOfDay(),DATE.atTime(23, 59))))
                .thenReturn(Collections.EMPTY_LIST);

        Assertions.assertEquals(Collections.EMPTY_LIST, service.getByDate(LocalDate.now()));
    }

}
