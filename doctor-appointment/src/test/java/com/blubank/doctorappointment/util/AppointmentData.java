package com.blubank.doctorappointment.util;

import com.blubank.doctorappointment.dto.AppointmentDTO;
import com.blubank.doctorappointment.dto.CreateAppointmentDTO;
import com.blubank.doctorappointment.dto.PatientDTO;
import com.blubank.doctorappointment.model.Appointment;
import com.blubank.doctorappointment.model.Patient;

import java.util.List;

import static com.blubank.doctorappointment.util.AppointmentSlotData.*;
import static com.blubank.doctorappointment.util.PatientData.*;

public class AppointmentData {


    public static Appointment appointment(){
        return Appointment.builder()
                .id(1L)
                .appointmentSlot(appointmentSlotData())
                .patient(Patient.builder().name(PatientData.NAME).phoneNumber(PHONE_NUMBER).build())
                .build();
    }
    public static Appointment appointmentWithoutId(){
        return Appointment.builder()
                .appointmentSlot(appointmentSlotData())
                .patient(Patient.builder().id(1L).name(PatientData.NAME).phoneNumber(PHONE_NUMBER)
                        .appointmentList(
                                List.of(Appointment.builder().appointmentSlot(appointmentSlotData()).build())
                        ).build())
                .build();
    }

    public static AppointmentDTO appointmentDTO(){
        return AppointmentDTO.builder()
                .id(1L)
                .appointmentSlot(appointmentSlotDTO())
                .patient(AppointmentDTO.PatientDTO.builder().name(PatientData.NAME).phoneNumber(PHONE_NUMBER).build())
                .build();
    }

    public static List<Appointment> appointmentList(){
        return List.of(appointment());
    }

    public static CreateAppointmentDTO createAppointmentDTO(){
        return CreateAppointmentDTO.builder()
                .appointmentSlot(appointmentSlotDTO())
                .patient(patientDTO())
                .build();
    }
}
