package com.gd.clinic.controller;

import com.gd.clinic.api.EventsApi;
import com.gd.clinic.model.EventResponseDto;
import com.gd.clinic.model.NewEventDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
@RestController
public class EventsController implements EventsApi {

    @Override
    public ResponseEntity<Void> cancelEvent(String eventId, String cancelReason) {
        return null;
    }

    @Override
    public ResponseEntity<List<EventResponseDto>> getAllEventsFor(String patientId) {
        return null;
    }

    @Override
    public ResponseEntity<EventResponseDto> getEventById(String eventId) {
        return null;
    }

    @Override
    public ResponseEntity<EventResponseDto> modEvent(String eventId, NewEventDto newEventDto) {
        return null;
    }

}
