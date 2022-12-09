package com.gd.clinic.generators;

public class TestGenRepo {

    private final String[] firstNames = {"Claire", "John", "Juan", "Sergio", "Arturo", "Jane", "Ronnie"};
    private final String[] lastNames = {"Seinfeld", "Engel", "Ryder", "Rogan", "Ruiz", "Olvera", "Joy"};
    private final String[] diagnosis = {"Cancer", "Gout", "COVID", "Diabetes", "Gangrene", "Herpes", "Gonorrhea"};
    private final String[] medicines = {"Penicillin", "Aspirin", "Tylenol", "Vicodin", "Xanax", "Ketamine", "Water"};
    private final String[] procedures = {"Radiography", "Colonoscopy", "Mastectomy", "Rhinoplasty", "Shock Therapy", "Love and Affection", "Hugs"};
    private final String[] cancelReasons = {"Not in the mood", "Ran Away", "Asked for Mercy", "Doctor's Orders", "Live and Let Die"};

    public String[] getFirstNames() {
        return firstNames;
    }

    public String[] getProcedures() {
        return procedures;
    }

    public String[] getLastNames() {
        return lastNames;
    }

    public String[] getMedicines() {
        return medicines;
    }

    public String[] getDiagnosis() {
        return diagnosis;
    }

    public String[] getCancelReasons() {
        return cancelReasons;
    }

}
