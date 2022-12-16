package com.gd.clinic.generators;

import com.gd.clinic.entity.Event;
import com.gd.clinic.entity.Prescription;
import com.gd.clinic.model.NewEventDto;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class EventGenerator implements TestCollectionGenerator<Event> {

    private static final TestGenRepo genRepo = new TestGenRepo();

    private Prescription prescription;

    @Override
    public Set<Event> generateObjects(int requestedQuantity) {
        Set<Event> result = new HashSet<>();
        for (int i = 0; i < requestedQuantity; i++) {
            Event genE = new Event();
            genE.setPrescription(prescription);
            genE.setStatus(getStatus());
            //TODO: Implement time scheduling based on prescriptions, modify entity and DTOs if necessary
            genE.setDateTime(OffsetDateTime.now());
            if (genE.getStatus().equals(NewEventDto.StatusEnum.CANCELLED)) {
                genE.setCancelReason(genRepo.getCancelReasons()[randomInt()]);
            }
            result.add(genE);
        }
        return result;
    }

    @Override
    public int randomInt() {
        return ThreadLocalRandom.current().nextInt(0, 5);
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public int calculateEvents() {
        int multiplier = 0;
        switch (this.prescription.getFrequencyUnit()) {
            case DAY -> multiplier = 1;
            case WEEK -> multiplier = prescription.getDaysOfTheWeek().size();
            case MONTH -> multiplier = prescription.getDaysOfTheWeek().size() * 4;
        }
        return (this.prescription.getFrequency()) * multiplier;
    }

    private NewEventDto.StatusEnum getStatus() {
        return NewEventDto.StatusEnum.values()[ThreadLocalRandom.current().nextInt(0, 3)];
    }

}
