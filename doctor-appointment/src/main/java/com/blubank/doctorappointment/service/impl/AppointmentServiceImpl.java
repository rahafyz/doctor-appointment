package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.dto.TimeDTO;
import com.blubank.doctorappointment.exception.CustomException;
import com.blubank.doctorappointment.model.AppointmentSlot;
import com.blubank.doctorappointment.repository.AppointmentSlotRepository;
import com.blubank.doctorappointment.service.AppointmentSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentSlotService {

    private final AppointmentSlotRepository repository;

    @Value("${doctor.appointment.time.interval}")
    private static int TIME_INTERVAL;

    @Override
    public List<AppointmentSlot> save(TimeDTO dto) {
        if (!timeValidation(dto))
            throw new CustomException("invalid time", HttpStatus.BAD_REQUEST);
        return repository.saveAll(setTimeInterval(dto));
    }

    private List<AppointmentSlot> setTimeInterval(TimeDTO dto) {
        List<AppointmentSlot> timeSlots = new ArrayList<>();

        long numSlots = Duration.between(dto.getStartTime(), dto.getEndTime()).toMinutes() / TIME_INTERVAL;

        if (numSlots == 0)
            return timeSlots;

        LocalDateTime slotTime = dto.getStartTime();
        for (int i = 0; i <= numSlots; i++) {
            AppointmentSlot slot = AppointmentSlot.builder()
                    .startTime(slotTime)
                    .endTime(slotTime.plusMinutes(30))
                    .isAvailable(true).build();
            timeSlots.add(slot);
            slotTime = slotTime.plusMinutes(30);
        }
        return timeSlots;
    }

    private boolean timeValidation(TimeDTO dto) {
        return dto.getStartTime().isBefore(dto.getEndTime());
    }
}
