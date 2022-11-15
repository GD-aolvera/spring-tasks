package com.gd.clinic.controller;

import com.gd.clinic.api.PatientsApi;
import com.gd.clinic.model.NewPatientDto;
import com.gd.clinic.model.PatientResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientsController implements PatientsApi {

    @Override
    public ResponseEntity<PatientResponseDto> addPatient(NewPatientDto newPatientDto) {
        return null;
    }

    @Override
    public ResponseEntity<PatientResponseDto> dischargePatientByInsurance(String patientId) {
        return null;
    }

    @Override
    public ResponseEntity<PatientResponseDto> editPatient(String patientId, NewPatientDto newPatientDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        return null;
    }

    @Override
    public ResponseEntity<PatientResponseDto> getPatient(String patientId) {
        return null;
    }

}
