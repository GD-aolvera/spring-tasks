package com.gd.clinic.generators;

import com.gd.clinic.entity.Patient;
import com.gd.clinic.entity.Prescription;
import com.gd.clinic.entity.Treatment;
import com.gd.clinic.enums.DaysEnum;
import com.gd.clinic.model.NewPrescriptionDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class PrescriptionGenerator implements TestCollectionGenerator<Prescription> {

    private List<Patient> patients;
    private List<Treatment> treatments;

    @Override
    public Set<Prescription> generateObjects(int requestedQuantity) {
        Set<Prescription> prescriptions = new HashSet<>();
        for (int i = 0; i < requestedQuantity; i++) {
            Prescription genP = new Prescription();
            genP.setPatient(patients.get(ThreadLocalRandom.current().nextInt(0, patients.size())));
            genP.setTreatment(treatments.get(ThreadLocalRandom.current().nextInt(0, treatments.size())));
            genP.setFrequency(randomInt());
            genP.setFrequencyUnit(getFreqUnit());
            genP.setDescription("This prescription was automatically generated");
            genP.setDaysOfTheWeek(getDOW());
            genP.setStatus(getStatus());
            prescriptions.add(genP);
        }
        return prescriptions;
    }

    @Override
    public int randomInt() {
        return ThreadLocalRandom.current().nextInt(0, 7);
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    private NewPrescriptionDto.FrequencyUnitEnum getFreqUnit() {
        NewPrescriptionDto.FrequencyUnitEnum unit = NewPrescriptionDto.FrequencyUnitEnum.DAY;
        int choice = ThreadLocalRandom.current().nextInt(1, 4);
        switch (choice) {
            case 1:
                break;
            case 2:
                unit = NewPrescriptionDto.FrequencyUnitEnum.WEEK;
                break;
            case 3:
                unit = NewPrescriptionDto.FrequencyUnitEnum.MONTH;
        }
        return unit;
    }

    private List<DaysEnum> getDOW() {
        List<DaysEnum> result = new ArrayList<>();
        int numberOfDays = randomInt();
        for (int i = 0; i < numberOfDays; i++) {
            int choice = randomInt();
            if(result.contains(DaysEnum.values()[choice])) {continue;}
            DaysEnum weekday = DaysEnum.values()[choice];
            result.add(weekday);
        }
        return result;
    }

    private NewPrescriptionDto.StatusEnum getStatus() {
        NewPrescriptionDto.StatusEnum status = NewPrescriptionDto.StatusEnum.ACTIVE;
        int choice = ThreadLocalRandom.current().nextInt(1,3);
        if (choice == 2){
            status = NewPrescriptionDto.StatusEnum.INACTIVE;
        }
        return status;
    }

}
