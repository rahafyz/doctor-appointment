package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.dto.AppointmentSlotDTO;
import com.blubank.doctorappointment.dto.TimeDTO;
import com.blubank.doctorappointment.exception.CustomException;
import com.blubank.doctorappointment.mapper.AppointmentSlotMapper;
import com.blubank.doctorappointment.model.AppointmentSlot;
import com.blubank.doctorappointment.repository.AppointmentSlotRepository;
import com.blubank.doctorappointment.service.AppointmentSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentSlotServiceImpl implements AppointmentSlotService {

    private final AppointmentSlotRepository repository;

    private final AppointmentSlotMapper mapper;

    @Value("${doctor.appointment.time.interval}")
    private static int timeInterval;

    @Override
    public AppointmentSlot getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new CustomException("no appointment found", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public List<AppointmentSlotDTO> save(TimeDTO dto) {
        if (!timeValidation(dto))
            throw new CustomException("invalid time", HttpStatus.BAD_REQUEST);
        return mapper.toDTOList(repository.saveAll(setTimeInterval(dto)));
    }

    @Override
    public List<AppointmentSlotDTO> getOpenAppointments(Long doctorId) {
        return mapper.toDTOList(repository.findByDoctor_IdAndIsAvailableTrue(doctorId));
    }

    @Override
    public void delete(Long id) {
        AppointmentSlot appointmentSlot = getById(id);
        if (!isAvailable(appointmentSlot))
            throw new CustomException("this appointment is taken", HttpStatus.NOT_ACCEPTABLE);
        repository.deleteById(id);
    }

    @Override
    public List<AppointmentSlotDTO> getByDate(LocalDate date) {
        LocalDateTime startTime = date.atStartOfDay();
        LocalDateTime endTime = date.atTime(23, 59);
        List<AppointmentSlot> appointmentSlots = repository.findByStartTimeBetween(startTime, endTime).stream()
                .filter(this::isAvailable).collect(Collectors.toList());
        return mapper.toDTOList(appointmentSlots);
    }


    private List<AppointmentSlot> setTimeInterval(TimeDTO dto) {
        List<AppointmentSlot> timeSlots = new ArrayList<>();

        long numSlots = Duration.between(dto.getStartTime(), dto.getEndTime()).toMinutes() / timeInterval;

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

    private boolean isAvailable(AppointmentSlot appointmentSlot) {
        return Boolean.TRUE.equals(appointmentSlot.getIsAvailable());
    }
}
