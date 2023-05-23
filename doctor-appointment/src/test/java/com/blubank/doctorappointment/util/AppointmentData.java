package com.blubank.doctorappointment.util;

import com.blubank.doctorappointment.dto.AppointmentDTO;
import com.blubank.doctorappointment.model.Appointment;
import com.blubank.doctorappointment.model.Patient;

import static com.blubank.doctorappointment.util.AppointmentSlotData.appointmentSlot;
import static com.blubank.doctorappointment.util.AppointmentSlotData.appointmentSlotDTO;
import static com.blubank.doctorappointment.util.PatientData.PHONE_NUMBER;

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
}
