package com.gd.clinic.builders;

import com.gd.clinic.entity.Patient;

import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class PatientGenerator implements TestCollection<Patient> {

    private TestGenRepo genRepo;

    @Override
    public Set<Patient> GenerateObjects(int requestedQuantity) {
        Set<Patient> result;
        for (int i = 0; i < requestedQuantity; i++) {
            Patient generatedPatient = new Patient();
            generatedPatient.setFirstName(genRepo.getFirstNames()[randomInt()]);
            generatedPatient.setLastName(genRepo.getLastNames()[randomInt()]);
            generatedPatient.setDiagnosis(genRepo.getDiagnosis()[randomInt()]);
            generatedPatient.setDoctorId(UUID.randomUUID());
            generatedPatient.setInsuranceNumber();
        }
    }

    @Override
    public int randomInt() {
        return ThreadLocalRandom.current().nextInt(0,7);
    }


}
