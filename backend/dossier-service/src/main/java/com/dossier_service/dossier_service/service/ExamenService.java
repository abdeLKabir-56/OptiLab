package com.dossier_service.dossier_service.service;

import com.dossier_service.dossier_service.entity.Examen;
import com.dossier_service.dossier_service.exception.ResourceNotFoundException;
import com.dossier_service.dossier_service.kafka.KafkaProducerService;
import com.dossier_service.dossier_service.repository.ExamenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ExamenService {

    private final KafkaProducerService kafkaProducerService;
    private final ExamenRepository examenRepository;

    public ExamenService(KafkaProducerService kafkaProducerService, ExamenRepository examenRepository) {
        this.kafkaProducerService = kafkaProducerService;
        this.examenRepository = examenRepository;
    }

    public Examen createExamen(Examen examen) {
        Examen savedExamen = examenRepository.save(examen);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String notificationMessage = mapper.writeValueAsString(savedExamen);
            kafkaProducerService.sendMessage("notification-topic", notificationMessage);
        } catch (Exception e) {
            log.error("Failed to send Kafka message for Examen ID: {}", savedExamen.getId(), e);
        }

        return savedExamen;
    }

    public List<Examen> getAllExamens() {
        return examenRepository.findAll();
    }

    public Examen findExamenById(Long id) {
        return examenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Examen Not Found with ID: " + id));
    }

    public Examen updateExamen(Long id, Examen examen) {
        return examenRepository.findById(id)
                .map(existingExamen -> {
                    existingExamen.setDossier(examen.getDossier());
                    existingExamen.setEpreuve(examen.getEpreuve());
                    existingExamen.setResultat(examen.getResultat());
                    existingExamen.setObservations(examen.getObservations());
                    return examenRepository.save(existingExamen);
                }).orElseThrow(() -> new ResourceNotFoundException("Examen Not Found with ID: " + id));
    }

    public void deleteExamen(Long id) {
        if (!examenRepository.existsById(id)) {
            throw new ResourceNotFoundException("Examen Not Found with ID: " + id);
        }
        examenRepository.deleteById(id);
    }
}
