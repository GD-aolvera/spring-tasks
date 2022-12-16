package com.gd.clinic.service;

import com.gd.clinic.entity.Prescription;
import com.gd.clinic.entity.Treatment;
import com.gd.clinic.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public Prescription getById(UUID id) {
        return prescriptionRepository.findById(id).orElseThrow(() -> new RuntimeException("Prescription not Found"));
    }

    public Prescription getByPatientId(UUID id) {
        return prescriptionRepository.findOneByPatientId(id).orElseThrow(() -> new RuntimeException("Prescription not Found"));
    }

    public Prescription getByTreatment(Treatment t) {
        return prescriptionRepository.findOneByTreatment(t).orElseThrow(() -> new RuntimeException("Prescription not Found"));
    }

    public Prescription save (Prescription prescription) throws RuntimeException {
        prescription.setDatePrescribed(OffsetDateTime.now());
        return prescriptionRepository.save(prescription);
    }

}
