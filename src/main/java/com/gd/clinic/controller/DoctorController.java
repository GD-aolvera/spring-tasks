package com.gd.clinic.controller;

import com.gd.clinic.api.DoctorsApi;
import com.gd.clinic.model.EventDto;
import com.gd.clinic.model.PatientDto;
import com.gd.clinic.model.PrescriptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController implements DoctorsApi {

  @Override
  public ResponseEntity<Void> addPatient(PatientDto patientDto) {
    return null;
  }

  @Override
  public ResponseEntity<Void> createPrescription(PrescriptionDto prescriptionDto) {
    return null;
  }

  @Override
  public ResponseEntity<Void> deletePrescriptionById(Long prescriptionId) {
    return null;
  }

  @Override
  public ResponseEntity<Void> dischargePatientByInsurance(Long insuranceNumber) {
    return null;
  }

  @Override
  public ResponseEntity<PatientDto> editPatient(Long patientId, String patientParameter, String parameterValue) {
    return null;
  }

  @Override
  public ResponseEntity<List<EventDto>> getAllEventsFor(Long patientId) {
    return null;
  }

  @Override
  public ResponseEntity<List<PatientDto>> getAllPatients() {
    return null;
  }

  @Override
  public ResponseEntity<PatientDto> getPatient(Long patientId) {
    return null;
  }

  @Override
  public ResponseEntity<PrescriptionDto> getPrescription(Long prescriptionId) {
    return null;
  }

  @Override
  public ResponseEntity<List<PrescriptionDto>> getPrescriptionsOf(Long patientId) {
    return null;
  }

  @Override
  public ResponseEntity<List<EventDto>> modEvent(Long eventId, String eventParameter, String parameterValue) {
    return null;
  }

  @Override
  public ResponseEntity<Void> modifyEvent(Long eventId, EventDto eventDto) {
    return null;
  }

  @Override
  public ResponseEntity<PrescriptionDto> modifyPrescription(Long prescriptionId, String prescriptionParameter, String parameterValue) {
    return null;
  }
}
