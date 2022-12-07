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



}
