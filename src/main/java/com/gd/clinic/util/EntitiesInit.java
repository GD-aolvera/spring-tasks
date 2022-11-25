/*package com.gd.clinic.util;

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
        patientService.save(p);
        Treatment t = new Treatment("Tylenol", "Medicine");
        treatmentRepository.save(t);
        Prescription pr = new Prescription(patientService.getByIN("ASD12345").get(), treatmentRepository.findOneByName("Tylenol").get(), "Twice a week", 2);
        prescriptionService.save(pr);
        Event e = new Event(prescriptionService.getByPatientId(patientService.getByIN("ASD12345").get().getId()).get(), patientService.getByIN("ASD12345").get(), OffsetDateTime.now(), EventResponseDto.StatusEnum.SCHEDULED);
        eventRepository.save(e);
    }

}
*/

