package com.mehdi.optilab.laboratoire;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaboratoireService {

    private final laboratoireRepository repository;
    private final laboratoireMapper mapper;

    public Long createLaboratoire(LaboratoireRequest request) {
        var laboratoire = mapper.toLaboratory(request);
        return repository.save(laboratoire).getId();
    }

    public LaboratoireResponse findById(Long laboratoryId) {
        return repository.findById(laboratoryId)
                .map(mapper::toLaboratoryResponse)
                .orElseThrow(()-> new EntityNotFoundException("Laboratory not found with the ID:: "+laboratoryId));
    }


    public List<LaboratoireResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toLaboratoryResponse)
                .collect(Collectors.toList());
    }
}
