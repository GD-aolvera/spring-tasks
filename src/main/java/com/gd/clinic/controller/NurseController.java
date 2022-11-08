package com.gd.clinic.controller;

import com.gd.clinic.api.NursesApi;
import com.gd.clinic.model.EventDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NurseController implements NursesApi {
    @Override
    public ResponseEntity<Void> cancelEvent(Long eventId, String cancelReason) {
        return null;
    }

    @Override
    public ResponseEntity<List<EventDto>> modEvent(Long eventId, String eventParameter, String parameterValue) {
        return null;
    }
}
