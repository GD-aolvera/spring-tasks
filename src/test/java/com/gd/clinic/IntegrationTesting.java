package com.gd.clinic;

import com.gd.clinic.entity.Event;
import com.gd.clinic.entity.Prescription;
import com.gd.clinic.generators.TestEntityGenerator;
import com.gd.clinic.containers.ContainersEnvironment;
import com.gd.clinic.entity.Patient;
import com.gd.clinic.entity.Treatment;
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

import java.util.Scanner;
import java.util.Set;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringTasksApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTesting extends ContainersEnvironment {

    private static final int SAMPLE_SIZE = 100;
    private static final int PRESCRIPTION_AMOUNT = 33;
    private static final TestEntityGenerator TEST_GEN = new TestEntityGenerator();
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
        Set<Treatment> treatments = TEST_GEN.generateTreatments(7);
        treatments.forEach(treatment -> treatmentRepository.save(treatment));
        Set<Patient> patients = TEST_GEN.generatePatients(SAMPLE_SIZE);
        patients.forEach(patient -> patientService.save(patient));
        Set<Prescription> prescriptions = TEST_GEN.generatePrescriptions(PRESCRIPTION_AMOUNT, patientService.getAll(), treatmentRepository.findAll());
        prescriptions.forEach(prescription -> prescriptionService.save(prescription));
        Set<Event> events = TEST_GEN.generateEvents(prescriptions);
        events.forEach(event -> eventRepository.save(event));
        //TODO: Uncomment to check db, use info in console to connect. Need to fix scanner, wait for user input to continue. Little help here!
        //checkDBContinue();
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
