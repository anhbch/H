package com.application.puranh.service.mapper;

import java.util.List;

public interface EntityMapper <D, E>{

    D toDto (E entity);

    E toEntity (D Dto);

    List<D> toListDto (List<E> listEntity);

    List<E> toListEntity (List<D> listDto);
}
