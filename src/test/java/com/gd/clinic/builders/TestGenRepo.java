package com.gd.clinic.builders;

import com.gd.clinic.model.NewTreatmentDto;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
public class TestGenRepo {

    private final String[] firstNames = {"Claire", "John", "Juan", "Sergio", "Arturo", "Jane", "Ronnie"};
    private final String[] lastNames = {"Seinfeld", "Engel", "Ryder", "Rogan", "Ruiz", "Olvera", "Joy"};
    private final String[] diagnosis = {"Cancer", "Gout", "COVID", "Diabetes", "Gangrene", "Herpes", "Gonorrhea"};
    private final String[] medicines = {"Penicillin", "Aspirin", "Tylenol", "Vicodin", "Xanax", "Ketamine", "Water"};
    private final String[] procedures = {"Radiography", "Colonoscopy", "Mastectomy", "Rhinoplasty", "Shock Therapy", "Love and Affection"};

    public String getTreatment() {
        int choice = ThreadLocalRandom.current().nextInt(1, 2);
        Enum type = NewTreatmentDto.TypeEnum.MEDICINE;
        String result = "NotFetched";
        switch (choice) {
            case 1 -> type = NewTreatmentDto.TypeEnum.MEDICINE;
            case 2 -> type = NewTreatmentDto.TypeEnum.PROCEDURE;
        }
        switch (type.toString()) {
            case  "medicine" -> result = medicines[ThreadLocalRandom.current().nextInt(0,7)];
            case "procedure" -> result = procedures [ThreadLocalRandom.current().nextInt(0, 7)];
        }
        return result;
    }

    public String getInsuranceNumber() {
        return "IMPLEMENT THIS";
    }

}
