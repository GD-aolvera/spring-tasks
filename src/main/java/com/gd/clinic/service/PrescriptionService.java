package com.gd.clinic.service;

import com.gd.clinic.entity.Prescription;
import com.gd.clinic.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public Optional<Prescription> getById (UUID id){
        return prescriptionRepository.findById(id);
    }

    public Prescription save (Prescription prescription) throws ClassNotFoundException {
        prescription.setDatePrescribed(OffsetDateTime.now());
        prescriptionRepository.save(prescription);
        Optional<Prescription> savedPrescription = prescriptionRepository.findById(prescription.getId());
        if(savedPrescription.isPresent()){
            return savedPrescription.get();
        } else {
            throw new ClassNotFoundException("The patient was not found after saving");
        }
    }

}
