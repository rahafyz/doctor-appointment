package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.Appointment;
import com.blubank.doctorappointment.model.AppointmentSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot,Long> {

    List<AppointmentSlot> findByAvailableTrue();

    List<AppointmentSlot> findByStartTimeBetween(LocalDateTime startTime,LocalDateTime endTime);
}
