package com.blubank.doctorappointment.util;

import com.blubank.doctorappointment.dto.ReserveAppointmentDTO;

import static com.blubank.doctorappointment.util.PatientData.createPatientDTO;
import static com.blubank.doctorappointment.util.PatientData.patientDTO;

public class AppointmentFacadeData {
    private static final Long ID = 1L;
    public static ReserveAppointmentDTO reserveAppointmentDTO(){
        return ReserveAppointmentDTO.
                builder().appointmentSlotId(ID)
                .patientDTO(createPatientDTO()).build();
    }
    public static ReserveAppointmentDTO reserveAppointmentDTO(Long appointmentSlotId){
        return ReserveAppointmentDTO.
                builder().appointmentSlotId(appointmentSlotId)
                .patientDTO(createPatientDTO()).build();
    }
}
