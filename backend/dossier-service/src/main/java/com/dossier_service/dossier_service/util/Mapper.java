package com.dossier_service.dossier_service.util;

public interface Mapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}

