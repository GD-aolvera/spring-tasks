package com.gd.clinic.generators;

import com.gd.clinic.entity.Patient;
import com.gd.clinic.model.NewPatientDto;
import com.gd.clinic.security.entity.User;
import com.gd.clinic.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class PatientGenerator implements TestCollectionGenerator<Patient> {

    private final TestGenRepo genRepo = new TestGenRepo();

    @Override
    public Set<Patient> generateObjects(int requestedQuantity) {
        Set<Patient> result = new HashSet<>();
        for (int i = 0; i < requestedQuantity; i++) {
            Patient generatedPatient = new Patient();
            generatedPatient.setFirstName(genRepo.getFirstNames()[randomInt()]);
            generatedPatient.setLastName(genRepo.getLastNames()[randomInt()]);
            generatedPatient.setDiagnosis(genRepo.getDiagnosis()[randomInt()]);
            generatedPatient.setDoctorId(new User());
            generatedPatient.setInsuranceNumber(getInsuranceNumber());
            generatedPatient.setStatus(getPatientStatus());
            generatedPatient.setBirthDate(OffsetDateTime.now());
            result.add(generatedPatient);
        }
        return result;
    }

    @Override
    public int randomInt() {
        return ThreadLocalRandom.current().nextInt(0,7);
    }

    private String getInsuranceNumber() {
        Random rnd = new Random();
        Integer rawIN = 100000000 + rnd.nextInt(900000000);
        String result = rawIN.toString().substring(0,3) + "-" + rawIN.toString().substring(3,5) + "-" + rawIN.toString().substring(5,9);
        return result;
    }

    private NewPatientDto.StatusEnum getPatientStatus(){
        int choice = ThreadLocalRandom.current().nextInt(1, 4);
        NewPatientDto.StatusEnum status = NewPatientDto.StatusEnum.IN_TREATMENT;
        switch (choice) {
            case 1:
                break;
            case 2 :
                status = NewPatientDto.StatusEnum.RECOVERED;
                break;
            case 3 :
                status = NewPatientDto.StatusEnum.DISCHARGED;
                break;
        }
        return status;
    }

}
