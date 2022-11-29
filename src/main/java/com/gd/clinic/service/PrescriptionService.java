package com.gd.clinic.service;

import com.gd.clinic.entity.Prescription;
import com.gd.clinic.entity.Treatment;
import com.gd.clinic.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public Prescription getById(UUID id) throws RuntimeException {
        Optional<Prescription> fetchedPrescription =  prescriptionRepository.findById(id);
        if(fetchedPrescription.isPresent()) {
            return fetchedPrescription.get();
        } else {
            throw new RuntimeException("Prescription not found by ID");
        }
    }

    public Prescription getByPatientId(UUID id) throws RuntimeException {
        Optional<Prescription> fetchedPrescription = prescriptionRepository.findOneByPatientId(id);
        if(fetchedPrescription.isPresent()) {
            return fetchedPrescription.get();
        } else {
            throw  new RuntimeException("Prescription not found by patient ID");
        }
    }

    public Prescription getByTreatment(Treatment t) throws RuntimeException {
        Optional<Prescription> fetchedPrescription = prescriptionRepository.findOneByTreatment(t);
        if(fetchedPrescription.isPresent()) {
            return fetchedPrescription.get();
        } else {
            throw new RuntimeException("Prescription not found solely by treatment");
        }
    }

    public Prescription save (Prescription prescription) throws RuntimeException {
        prescription.setDatePrescribed(OffsetDateTime.now());
        prescriptionRepository.save(prescription);
        Optional<Prescription> savedPrescription = prescriptionRepository.findOneByPatientId(prescription.getPatient().getId());
        if(savedPrescription.isPresent()){
            return savedPrescription.get();
        } else {
            throw new RuntimeException("Prescription saved instructions executed, prescription not found while fetching");
        }
    }

}
