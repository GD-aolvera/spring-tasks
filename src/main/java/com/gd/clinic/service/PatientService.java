package com.gd.clinic.service;

import com.gd.clinic.entity.Patient;
import com.gd.clinic.repository.PatientRepository;
import com.gd.clinic.security.util.AuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient getById(UUID id) throws RuntimeException {
        return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient Not Found"));
    }

    public boolean existsByInsuranceNumber(String in) {
        return patientRepository.findOneByInsuranceNumber(in).isPresent();
    }

    public Patient save(Patient patient) {
        patient.setCreatedBy(AuthInfo.getCurrentUser());
        patient.setCreatedAt(OffsetDateTime.now());
        return patientRepository.save(patient);
    }

    public Patient getByInsuranceNumber(String in) {
        return patientRepository.findOneByInsuranceNumber(in).orElseThrow(() -> new RuntimeException("Patient Not Found"));
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

}
