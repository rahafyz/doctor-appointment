package com.blubank.doctorappointment.util;

import com.blubank.doctorappointment.dto.AppointmentDTO;
import com.blubank.doctorappointment.dto.CreateAppointmentDTO;
import com.blubank.doctorappointment.dto.PatientDTO;
import com.blubank.doctorappointment.model.Appointment;
import com.blubank.doctorappointment.model.Patient;

import java.util.List;

import static com.blubank.doctorappointment.util.AppointmentSlotData.appointmentSlot;
import static com.blubank.doctorappointment.util.AppointmentSlotData.appointmentSlotDTO;
import static com.blubank.doctorappointment.util.PatientData.*;

public class AppointmentData {


    public static Appointment appointment(){
        return Appointment.builder()
                .id(1L)
                .appointmentSlot(appointmentSlot())
                .patient(Patient.builder().name(PatientData.NAME).phoneNumber(PHONE_NUMBER).build())
                .build();
    }

    public static AppointmentDTO appointmentDTO(){
        return AppointmentDTO.builder()
                .appointmentSlot(appointmentSlotDTO())
                .patient(AppointmentDTO.PatientDTO.builder().name(PatientData.NAME).phoneNumber(PHONE_NUMBER).build())
                .build();
    }
    public static List<AppointmentDTO> appointmentDTOList(){
        return List.of(appointmentDTO());
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
