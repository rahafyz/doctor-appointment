package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.dto.AppointmentSlotDTO;
import com.blubank.doctorappointment.dto.CreateAppointmentSlotDTO;
import com.blubank.doctorappointment.service.AppointmentSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/appointment-slot")
@RequiredArgsConstructor
public class AppointmentSlotController {

    private final AppointmentSlotService appointmentSlotService;

    @PostMapping("")
    public ResponseEntity<List<AppointmentSlotDTO>> save(@RequestBody @Valid CreateAppointmentSlotDTO timeDTO) {
        return ResponseEntity.ok(appointmentSlotService.save(timeDTO));
    }

    @GetMapping("/appointments/{doctorId}")
    public ResponseEntity<List<AppointmentSlotDTO>> getOpenAppointments(@PathVariable long doctorId) {
        return ResponseEntity.ok(appointmentSlotService.getOpenAppointments(doctorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        appointmentSlotService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentSlotDTO>> getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentSlotService.getByDate(date));
    }
}
