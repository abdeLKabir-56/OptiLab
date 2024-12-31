package com.dossier_service.dossier_service.service;

import com.dossier_service.dossier_service.dto.NotificationDTO;
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

        String dossierEmail = null;
        try {
            if (savedExamen.getEpreuve() != null &&
                    savedExamen.getEpreuve().getAnalyse() != null &&
                    savedExamen.getEpreuve().getAnalyse().getDossier() != null) {
                dossierEmail = savedExamen.getEpreuve().getAnalyse().getDossier().getFkEmailUtilisateur();
            }

            if (dossierEmail == null) {
                log.warn("Dossier email is null for Examen ID: {}", savedExamen.getId());
            }

            ObjectMapper mapper = new ObjectMapper();
            NotificationDTO message = new NotificationDTO(
                    savedExamen.getId(),
                    dossierEmail,
                    "An exam has been added to your dossier."
            );
            String notificationMessage = mapper.writeValueAsString(message);
            kafkaProducerService.sendMessage("notification-topic", notificationMessage);
        } catch (Exception e) {
            log.error("Failed to send Kafka message for Examen ID: {}. Email: {}", savedExamen.getId(), dossierEmail, e);
        }

        return savedExamen;
    }


    public List<Examen> getAllExamens() {
        return examenRepository.findAll();
    }

    public Examen findExamenById(Long id) {
        return examenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Examen not found with ID: " + id));
    }

    public Examen updateExamen(Long id, Examen examen) {
        return examenRepository.findById(id)
                .map(existingExamen -> {
                    existingExamen.setResultat(examen.getResultat());
                    existingExamen.setObservations(examen.getObservations());
                    return examenRepository.save(existingExamen);
                }).orElseThrow(() -> new ResourceNotFoundException("Examen not found with ID: " + id));
    }

    public void deleteExamen(Long id) {
        if (!examenRepository.existsById(id)) {
            throw new ResourceNotFoundException("Examen not found with ID: " + id);
        }
        examenRepository.deleteById(id);
    }
}
