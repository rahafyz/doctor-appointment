package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentSlotRepository extends JpaRepository<Appointment,Long> {
}
