package com.gd.clinic.controller;

import com.gd.clinic.api.TreatmentsApi;
import com.gd.clinic.model.NewTreatmentDto;
import com.gd.clinic.model.TreatmentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TreatmentsController implements TreatmentsApi {

    @Override
    public ResponseEntity<TreatmentResponseDto> addTreatment(NewTreatmentDto newTreatmentDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteTreatment(String treatmentID) {
        return null;
    }

    @Override
    public ResponseEntity<List<TreatmentResponseDto>> getAllTreatments() {
        return null;
    }

    @Override
    public ResponseEntity<TreatmentResponseDto> getTreatmentById(String treatmentID) {
        return null;
    }

    @Override
    public ResponseEntity<TreatmentResponseDto> modifyTreatment(String treatmentID, NewTreatmentDto newTreatmentDto) {
        return null;
    }
}
