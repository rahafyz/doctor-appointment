package com.blubank.doctorappointment.service.impl;

import com.blubank.doctorappointment.dto.AppointmentSlotDTO;
import com.blubank.doctorappointment.dto.CreateAppointmentSlotDTO;
import com.blubank.doctorappointment.exception.CustomException;
import com.blubank.doctorappointment.mapper.AppointmentSlotMapper;
import com.blubank.doctorappointment.model.AppointmentSlot;
import com.blubank.doctorappointment.repository.AppointmentSlotRepository;
import com.blubank.doctorappointment.service.AppointmentSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentSlotServiceImpl implements AppointmentSlotService {

    private final AppointmentSlotRepository repository;

    private final AppointmentSlotMapper mapper;
    private final Predicate<CreateAppointmentSlotDTO> isTimeValid = createAppointmentSlotDTO ->
            createAppointmentSlotDTO.getStartTime().isBefore(createAppointmentSlotDTO.getEndTime());

    private final Predicate<AppointmentSlot> isAvailable = appointmentSlot -> Boolean.TRUE.equals(appointmentSlot.getIsAvailable());

    //    @Value("${doctor.appointment.time.interval}")
    private int timeInterval = 30;

    @Override
    @Transactional(readOnly = true)
    public AppointmentSlot getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new CustomException("no appointment found", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    @Transactional
    public List<AppointmentSlotDTO> save(CreateAppointmentSlotDTO dto) {
        if (!isTimeValid.test(dto))
            throw new CustomException("invalid time", HttpStatus.BAD_REQUEST);
        if (setTimeInterval(dto).isEmpty())
            return Collections.emptyList();
        return mapper.toDTOList(repository.saveAll(setTimeInterval(dto)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentSlotDTO> getOpenAppointments(Long doctorId) {
        return mapper.toDTOList(repository.findByDoctor_IdAndIsAvailableTrue(doctorId));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AppointmentSlot appointmentSlot = getById(id);
        if (!isAvailable.test(appointmentSlot))
            throw new CustomException("this appointment is taken", HttpStatus.NOT_ACCEPTABLE);
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentSlotDTO> getByDate(LocalDate date) {
        LocalDateTime startTime = date.atStartOfDay();
        LocalDateTime endTime = date.atTime(23, 59);
        List<AppointmentSlot> appointmentSlots = repository.findByStartTimeBetween(startTime, endTime).stream()
                .filter(isAvailable).collect(Collectors.toList());
        return mapper.toDTOList(appointmentSlots);
    }


    private List<AppointmentSlot> setTimeInterval(CreateAppointmentSlotDTO dto) {
        List<AppointmentSlot> timeSlots = new ArrayList<>();

        long numSlots = Duration.between(dto.getStartTime(), dto.getEndTime()).toMinutes() / timeInterval;

        if (numSlots == 0)
            return timeSlots;

        LocalDateTime slotTime = dto.getStartTime();
        for (int i = 0; i < numSlots; i++) {
            AppointmentSlot slot = AppointmentSlot.builder()
                    .startTime(slotTime)
                    .endTime(slotTime.plusMinutes(30))
                    .isAvailable(true).build();
            timeSlots.add(slot);
            slotTime = slotTime.plusMinutes(30);
        }
        return timeSlots;
    }
}
