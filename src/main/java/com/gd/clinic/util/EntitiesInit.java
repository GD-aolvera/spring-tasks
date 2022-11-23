package com.gd.clinic.util;

import com.gd.clinic.entity.*;
import com.gd.clinic.model.EventResponseDto;
import com.gd.clinic.model.PatientResponseDto;
import com.gd.clinic.repository.*;
import com.gd.clinic.service.PatientService;
import com.gd.clinic.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
public class EntitiesInit implements CommandLineRunner {

    @Autowired
    PatientService patientService;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    PrescriptionService prescriptionService;
    @Autowired
    TreatmentRepository treatmentRepository;


    @Override
    public void run(String... args) throws Exception {
        Patient p = new Patient("John", "Cancer", "ASD12345");
        p.setStatus(PatientResponseDto.StatusEnum.RECOVERED.getValue());
        p.setDoctorId(UUID.randomUUID());
        Event e = new Event(UUID.randomUUID(), UUID.randomUUID(), OffsetDateTime.now(), EventResponseDto.StatusEnum.SCHEDULED);
        Prescription pr = new Prescription(UUID.randomUUID(), UUID.randomUUID(), "Twice a week", 2);
        Treatment t = new Treatment("Tylenol", "Medicine");
        eventRepository.save(e);
        patientService.save(p);
        prescriptionService.save(pr);
        treatmentRepository.save(t);
    }
}
