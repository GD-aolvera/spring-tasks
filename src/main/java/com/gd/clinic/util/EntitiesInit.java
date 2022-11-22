package com.gd.clinic.util;

import com.gd.clinic.entities.*;
import com.gd.clinic.model.PatientResponseDto;
import com.gd.clinic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
public class EntitiesInit implements CommandLineRunner {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    PrescriptionRepository prescriptionRepository;
    @Autowired
    TreatmentRepository treatmentRepository;


    @Override
    public void run(String... args) throws Exception {
        Patient p = new Patient("John", "Cancer", "ASD12345");
        p.setStatus(PatientResponseDto.StatusEnum.RECOVERED.getValue());
        p.setDoctorId(UUID.randomUUID());
        Event e = new Event(UUID.randomUUID(), UUID.randomUUID(), OffsetDateTime.now(), "Scheduled");
        Prescription pr = new Prescription(UUID.randomUUID(), UUID.randomUUID(), "Twice a week", 2);
        Treatment t = new Treatment("Tylenol", "Medicine");
        eventRepository.save(e);
        patientRepository.save(p);
        prescriptionRepository.save(pr);
        treatmentRepository.save(t);
    }
}
