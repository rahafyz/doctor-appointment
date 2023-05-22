package com.blubank.doctorappointment.mapper;

import com.blubank.doctorappointment.dto.AppointmentSlotDTO;
import com.blubank.doctorappointment.model.AppointmentSlot;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentSlotMapper {
    AppointmentSlotDTO toDTO(AppointmentSlot appointmentSlot);

    List<AppointmentSlotDTO> toDTOList(List<AppointmentSlot> appointmentSlots);
}
