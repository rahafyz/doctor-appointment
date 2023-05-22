package com.blubank.doctorappointment.mapper;

import com.blubank.doctorappointment.dto.AppointmentSlotDTO;
import com.blubank.doctorappointment.model.AppointmentSlot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentSlotMapper extends EntityMapper<AppointmentSlot,AppointmentSlotDTO>{

}
