package com.gd.clinic.service;

import com.gd.clinic.entity.Patient;
import com.gd.clinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
// TODO: Uncomment once security has been merged to this branch or vice versa
//import org.springframework.security.core.context.SecurityContextHolder;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;


    public Patient getById(UUID id) throws RuntimeException {
        Optional<Patient> fetchedPatient = patientRepository.findById(id);
        if(fetchedPatient.isPresent()) {
            return fetchedPatient.get();
        } else {
            throw  new RuntimeException("Patient not found");
        }
    }

    public boolean existsByInsuranceNumber(String in) {
        return patientRepository.findOneByInsuranceNumber(in).isPresent();
    }

    public Patient save(Patient patient) throws RuntimeException {
        //TODO: Uncomment once branch "security" has been merged to this branch or vice versa
        //patient.setCreatedBy(getCurrentUser());
        patient.setCreatedAt(OffsetDateTime.now());
        patientRepository.save(patient);
        Optional<Patient> savedPatient = patientRepository.findOneByInsuranceNumber(patient.getInsuranceNumber());
        if (savedPatient.isPresent()){
            return savedPatient.get();
        } else {
            throw new RuntimeException("Patient save instruction executed, patient not found while fetching");
        }
    }

    public Patient getByIN(String in) throws  RuntimeException {
        Optional<Patient> fetchedPatient = patientRepository.findOneByInsuranceNumber(in);
        if(fetchedPatient.isPresent()) {
            return fetchedPatient.get();
        } else {
            throw  new RuntimeException("Patient not found");
        }
    }

    //TODO: Uncomment once branch "security" has been merged to this branch or vice versa
   /*private String getCurrentUser() {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return principal.substring(principal.indexOf("userName=") + 9, principal.indexOf(", pass"));
    }*/

}
