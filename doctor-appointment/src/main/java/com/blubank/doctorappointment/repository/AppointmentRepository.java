package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.Appointment;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "appointment-cache")
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @CacheEvict(allEntries = true)
    @Override
    <S extends Appointment> S save(S entity);

    @Cacheable(key = "#root.methodName")
    @Override
    List<Appointment> findAll();
}
