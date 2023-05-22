package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.dto.AppointmentSlotDTO;
import com.blubank.doctorappointment.dto.TimeDTO;
import com.blubank.doctorappointment.service.AppointmentSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/appointment-slot")
@RequiredArgsConstructor
public class AppointmentSlotController {

    private AppointmentSlotService appointmentSlotService;

    @PostMapping("/api/v1")
    public ResponseEntity<List<AppointmentSlotDTO>> save(@RequestBody @Valid TimeDTO timeDTO) {
        return ResponseEntity.ok(appointmentSlotService.save(timeDTO));
    }

    @GetMapping("/api/v1")
    public ResponseEntity<List<AppointmentSlotDTO>> getOpenAppointments() {
        return ResponseEntity.ok(appointmentSlotService.getOpenAppointments());
    }

    @DeleteMapping("/api/v1/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        appointmentSlotService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/v1/appointments")
    public ResponseEntity<List<AppointmentSlotDTO>> getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentSlotService.getByDate(date));
    }
}
