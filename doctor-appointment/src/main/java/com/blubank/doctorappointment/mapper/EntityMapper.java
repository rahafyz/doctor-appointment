package com.blubank.doctorappointment.mapper;

import java.util.List;

public interface EntityMapper<T,D>{
    T toEntity(D dto);
    D toDTO(T entity);

    List<T> toEntityList(List<D> dto);
    List<D> toDTOList(List<T> entity);
}
