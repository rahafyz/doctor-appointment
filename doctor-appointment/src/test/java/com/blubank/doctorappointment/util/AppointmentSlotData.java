package com.blubank.doctorappointment.util;

import com.blubank.doctorappointment.dto.AppointmentSlotDTO;
import com.blubank.doctorappointment.dto.CreateAppointmentSlotDTO;
import com.blubank.doctorappointment.model.AppointmentSlot;
import com.blubank.doctorappointment.model.Doctor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentSlotData {

    public static final LocalDate DATE = LocalDate.now();
    private static final LocalDateTime START_TIME = DATE.atStartOfDay();
    private static final LocalDateTime END_TIME = START_TIME.plusMinutes(30);
    private static final LocalDateTime CREATE_START_TIME = DATE.atStartOfDay();
    private static final LocalDateTime CREATE_END_TIME = CREATE_START_TIME.plusMinutes(33);
    private static final LocalDateTime INVALID_CREATE_END_TIME = DATE.atStartOfDay().minusHours(1);

    public static AppointmentSlot appointmentSlot(){
        return AppointmentSlot.builder()
                .id(1L)
                .startTime(START_TIME)
                .endTime(END_TIME)
                .isAvailable(true)
                .doctor(Doctor.builder().id(1L).build())
                .build();
    }
    public static AppointmentSlot takenAppointmentSlot(){
        return AppointmentSlot.builder()
                .id(1L)
                .startTime(START_TIME)
                .endTime(END_TIME)
                .isAvailable(false)
                .doctor(Doctor.builder().id(1L).build())
                .build();
    }

    public static AppointmentSlotDTO appointmentSlotDTO(){
        return AppointmentSlotDTO.builder()
                .startTime(START_TIME)
                .endTime(END_TIME)
                .build();
    }
    public static List<AppointmentSlotDTO> appointmentSlotDTOList(){
        return List.of(appointmentSlotDTO());
    }

    public static CreateAppointmentSlotDTO createAppointmentSlotDTO(){
        return CreateAppointmentSlotDTO.builder()
                .startTime(CREATE_START_TIME)
                .endTime(CREATE_END_TIME).build();
    }
    public static CreateAppointmentSlotDTO createInvalidAppointmentSlotDTO(){
        return CreateAppointmentSlotDTO.builder()
                .startTime(CREATE_START_TIME)
                .endTime(INVALID_CREATE_END_TIME).build();
    }
    public static CreateAppointmentSlotDTO createShortAppointmentSlotDTO(){
        return CreateAppointmentSlotDTO.builder()
                .startTime(CREATE_START_TIME)
                .endTime(CREATE_START_TIME.plusMinutes(10)).build();
    }
}