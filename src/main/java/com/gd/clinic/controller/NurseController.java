package com.gd.clinic.controller;

import com.gd.clinic.api.NursesApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NurseController implements NursesApi {
    @Override
    public ResponseEntity<Void> cancelEvent(String eventId, String cancelReason) {
        return null;
    }
}
