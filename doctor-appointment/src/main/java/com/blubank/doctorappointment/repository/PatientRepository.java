package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.Appointment;
import com.blubank.doctorappointment.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
}
