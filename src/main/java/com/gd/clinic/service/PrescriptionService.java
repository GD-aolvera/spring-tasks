package com.gd.clinic.service;

import com.gd.clinic.entity.Prescription;
import com.gd.clinic.entity.Treatment;
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

    public Optional<Prescription> getById(UUID id){
        return prescriptionRepository.findById(id);
    }

    public Optional<Prescription> getByPatientId(UUID id) {
        return prescriptionRepository.findOneByPatientId(id);
    }

    public Optional<Prescription> getByTreatment(Treatment t) { return prescriptionRepository.findOneByTreatment(t); }

    public void save (Prescription prescription)  {
        prescription.setDatePrescribed(OffsetDateTime.now());
        prescriptionRepository.save(prescription);
    }

}
