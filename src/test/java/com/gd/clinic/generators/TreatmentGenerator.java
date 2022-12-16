package com.gd.clinic.generators;

import com.gd.clinic.entity.Treatment;
import com.gd.clinic.model.NewTreatmentDto;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class TreatmentGenerator implements TestCollectionGenerator<Treatment> {

    private static final TestGenRepo genRepo = new TestGenRepo();

    @Override
    public Set<Treatment> generateObjects(int requestedQuantity) {
        Set<Treatment> result = new HashSet<>();
        for (int i = 0; i < requestedQuantity; i++) {
            Treatment generatedTreatment = generateTreatment();
            result.add(generatedTreatment);
        }
        return result;
    }

    @Override
    public int randomInt() {
        return ThreadLocalRandom.current().nextInt(0,7);
    }

    private Treatment generateTreatment() {
        Treatment result = new Treatment();
        int choice = ThreadLocalRandom.current().nextInt(1, 3);
        NewTreatmentDto.TypeEnum type = NewTreatmentDto.TypeEnum.MEDICINE;
        String name = "Not Fetched";
        if (choice == 2) {
            type = NewTreatmentDto.TypeEnum.PROCEDURE;
        }
        switch (type.toString()) {
            case  "Medicine" -> name = genRepo.getMedicines()[randomInt()];
            case "Procedure" -> name = genRepo.getProcedures()[randomInt()];
        }
        result.setName(name);
        result.setType(type);
        return result;
    }

}
