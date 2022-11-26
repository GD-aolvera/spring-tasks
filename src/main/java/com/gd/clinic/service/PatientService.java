package com.gd.clinic.service;

import com.gd.clinic.entity.Patient;
import com.gd.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// TODO: Uncomment once security has been merged to this branch or vice versa
//import org.springframework.security.core.context.SecurityContextHolder;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;


    public Optional<Patient> getById(UUID id) {
        return patientRepository.findById(id);
    }

    public boolean existsByInsuranceNumber(String in) {
        return patientRepository.findOneByInsuranceNumber(in).isPresent();
    }

    public void save(Patient patient) {
        //TODO: Uncomment once branch "security" has been merged to this branch or vice versa
        //patient.setCreatedBy(getCurrentUser());
        patient.setCreatedAt(OffsetDateTime.now());
        patientRepository.save(patient);
    }

    public Optional<Patient> getByIN(String in) {
        return patientRepository.findOneByInsuranceNumber(in);
    }

    //TODO: Uncomment once branch "security" has been merged to this branch or vice versa
   /*private String getCurrentUser() {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return principal.substring(principal.indexOf("userName=") + 9, principal.indexOf(", pass"));
    }*/

}
