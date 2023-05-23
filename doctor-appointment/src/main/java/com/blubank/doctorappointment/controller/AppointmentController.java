package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.dto.AppointmentDTO;
import com.blubank.doctorappointment.dto.ReserveAppointmentDTO;
import com.blubank.doctorappointment.service.AppointmentFacadeService;
import com.blubank.doctorappointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentFacadeService appointmentFacadeService;

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(appointmentService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> reserve(@RequestBody @Valid ReserveAppointmentDTO reserveAppointmentDTO){
        return ResponseEntity.ok(appointmentFacadeService.reserveAppointment(reserveAppointmentDTO));
    }
}
