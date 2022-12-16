package com.gd.clinic.generators;

import com.gd.clinic.entity.Event;
import com.gd.clinic.entity.Patient;
import com.gd.clinic.entity.Prescription;
import com.gd.clinic.entity.Treatment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestEntityGenerator {

    public Set<Treatment> generateTreatments(int requestedQuantity){
        TreatmentGenerator gen = new TreatmentGenerator();
        return gen.generateObjects(requestedQuantity);
    }

    public Set<Patient> generatePatients(int requestedQuantity){
        PatientGenerator gen = new PatientGenerator();
        return gen.generateObjects(requestedQuantity);
    }

    public Set<Prescription> generatePrescriptions(int requested, List<Patient> p, List<Treatment> t) {
        PrescriptionGenerator gen = new PrescriptionGenerator();
        gen.setPatients(p);
        gen.setTreatments(t);
        return gen.generateObjects(requested);
    }

    public Set<Event> generateEvents(Set<Prescription> prescriptions) {
        Set<Event> result = new HashSet<>();
        EventGenerator gen = new EventGenerator();
        prescriptions.forEach(prescription -> {
            gen.setPrescription(prescription);
            gen.generateObjects(gen.calculateEvents()).forEach(event -> result.add(event));
        });
        return result;
    }

}
