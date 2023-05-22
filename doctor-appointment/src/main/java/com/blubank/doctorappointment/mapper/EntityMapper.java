package com.blubank.doctorappointment.mapper;

import java.util.List;

public interface EntityMapper<T,D>{
    T toEntity(D dto);
    D toDto(T entity);

    List<T> toListEntity(List<D> dto);
    List<D> toListDto(List<T> entity);
}
