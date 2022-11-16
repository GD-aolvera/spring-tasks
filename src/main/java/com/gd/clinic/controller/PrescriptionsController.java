package com.gd.clinic.controller;

import com.gd.clinic.api.PrescriptionsApi;
import com.gd.clinic.model.NewPrescriptionDto;
import com.gd.clinic.model.PrescriptionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrescriptionsController implements PrescriptionsApi {

    @PreAuthorize("hasRole('DOCTOR')")
    @Override
    public ResponseEntity<PrescriptionResponseDto> createPrescription(String patientId, NewPrescriptionDto newPrescriptionDto) {
        return null;
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @Override
    public ResponseEntity<Void> deletePrescriptionById(String prescriptionId) {
        return null;
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @Override
    public ResponseEntity<PrescriptionResponseDto> getPrescription(String prescriptionId) {
        return null;
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @Override
    public ResponseEntity<List<PrescriptionResponseDto>> getPrescriptionsOf(String patientId) {
        return null;
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @Override
    public ResponseEntity<PrescriptionResponseDto> modifyPrescription(String prescriptionId, NewPrescriptionDto newPrescriptionDto) {
        return null;
    }

}
