package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.dto.PatientAppointmentDTO;
import com.blubank.doctorappointment.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/api/v1/appointments")
    public ResponseEntity<PatientAppointmentDTO> getAppointments(@RequestParam String phoneNumber){
        return ResponseEntity.ok(patientService.getAppointments(phoneNumber));
    }

}
