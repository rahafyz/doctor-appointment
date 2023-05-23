package com.blubank.doctorappointment.util;

import com.blubank.doctorappointment.model.Appointment;
import com.blubank.doctorappointment.model.Patient;

import static com.blubank.doctorappointment.util.AppointmentSlotData.appointmentSlot;

public class AppointmentData {


    public static Appointment appointment(){
        return Appointment.builder()
                .id(1L)
                .appointmentSlot(appointmentSlot()).build();
    }
}
