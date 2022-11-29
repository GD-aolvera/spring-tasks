package com.gd.clinic;

import com.gd.clinic.containers.ContainersEnvironment;
import com.gd.clinic.entity.Event;
import com.gd.clinic.entity.Patient;
import com.gd.clinic.entity.Prescription;
import com.gd.clinic.entity.Treatment;
import com.gd.clinic.model.NewEventDto;
import com.gd.clinic.model.NewPatientDto;
import com.gd.clinic.model.NewPrescriptionDto;
import com.gd.clinic.model.NewTreatmentDto;
import com.gd.clinic.repository.EventRepository;
import com.gd.clinic.repository.TreatmentRepository;
import com.gd.clinic.service.PatientService;
import com.gd.clinic.service.PrescriptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringTasksApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTesting extends ContainersEnvironment {

    @Autowired
    private PatientService patientService;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void integralTest() throws InterruptedException {
        Treatment [] treatments = generateTreatments();
        Patient p = generatePatient(treatments);
        generateEvents(p.getPrescriptionList());
        checkDBContinue();
    }

    private void checkDBContinue() throws InterruptedException {
        System.out.println("Check Database, when ready to continue type 'y'");
        System.out.println(postgreSQLContainer.getJdbcUrl());
        System.out.println(postgreSQLContainer.getFirstMappedPort());
        System.out.println("usr: " + postgreSQLContainer.getUsername() + " " + "pass: " + postgreSQLContainer.getPassword());
        Scanner scan = new Scanner(System.in);
        synchronized (scan){
            scan.wait();
            String carryOn = scan.nextLine();
            if(carryOn.equals("y")){
                carryOn.notify();
            }
        }
    }

    private Treatment[] generateTreatments() {
        Treatment[] result =  new Treatment[2];
        result[0] = new Treatment("Vicodin", NewTreatmentDto.TypeEnum.MEDICINE);
        result[1] = new Treatment("Physical Therapy", NewTreatmentDto.TypeEnum.PROCEDURE);
        treatmentRepository.save(result[0]);
        treatmentRepository.save(result[1]);
        return result;
    }

    private Set<Prescription> generatePrescriptions (Treatment[] treatments, Patient p){
        Set<Prescription> prescriptionSet = new HashSet<>();
        Prescription pr1 = new Prescription(p, treatments[0], "Twice a day", 3);
        Prescription pr2 = new Prescription(p, treatments[1], "Twice a week", 30);
        pr1.setStatus(NewPrescriptionDto.StatusEnum.ACTIVE);
        pr2.setStatus(NewPrescriptionDto.StatusEnum.ACTIVE);
        prescriptionService.save(pr1);
        prescriptionService.save(pr2);
        prescriptionSet.add(pr1);
        prescriptionSet.add(pr2);
        return prescriptionSet;
    }

    private Patient generatePatient(Treatment[] treatments) {
        Patient p = new Patient("John", "Smith", "Cancer", "ASO@#4134", OffsetDateTime.now());
        patientService.save(p);
        p.setPrescriptionList(generatePrescriptions(treatments, p));
        p.setStatus(NewPatientDto.StatusEnum.IN_TREATMENT);
        patientService.save(p);
        return p;
    }

    private Event[] generateEvents(Set<Prescription> prescriptions){
        Event e1 = new Event((Prescription) prescriptions.stream().toArray()[1], OffsetDateTime.now());
        Event e2 = new Event((Prescription) prescriptions.stream().toArray()[0], OffsetDateTime.now());
        e1.setStatus(NewEventDto.StatusEnum.SCHEDULED);
        e2.setStatus(NewEventDto.StatusEnum.DONE);
        eventRepository.save(e1);
        eventRepository.save(e2);
        return new Event[] {e1,e2};
    }

}
