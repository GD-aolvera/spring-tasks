package com.gd.clinic.controller;

import com.gd.clinic.api.PatientsApi;
import com.gd.clinic.model.NewPatientDto;
import com.gd.clinic.model.PatientResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientsController implements PatientsApi {

    @PreAuthorize("hasRole('DOCTOR')")
    @Override
    public ResponseEntity<PatientResponseDto> addPatient(NewPatientDto newPatientDto) {
        return null;
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @Override
    public ResponseEntity<PatientResponseDto> dischargePatientById(String patientId) {
        return null;
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @Override
    public ResponseEntity<PatientResponseDto> editPatient(String patientId, NewPatientDto newPatientDto) {
        return null;
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @Override
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        return null;
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @Override
    public ResponseEntity<PatientResponseDto> getPatient(String patientId) {
        return null;
    }
}
