package com.gd.clinic.controller;

import com.gd.clinic.api.DoctorsApi;
import com.gd.clinic.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController implements DoctorsApi {

  @Override
  public ResponseEntity<PatientDto> addPatient(NewPatientDto newPatientDto) {
    return null;
  }

  @Override
  public ResponseEntity<PrescriptionDto> createPrescription(String patientId, NewPrescriptionDto newPrescriptionDto) {
    return null;
  }

  @Override
  public ResponseEntity<Void> deletePrescriptionById(String prescriptionId) {
    return null;
  }

  @Override
  public ResponseEntity<Void> dischargePatientByInsurance(String patientId) {
    return null;
  }

  @Override
  public ResponseEntity<PatientDto> editPatient(String patientId, NewPatientDto newPatientDto) {
    return null;
  }

  @Override
  public ResponseEntity<List<EventDto>> getAllEventsFor(String patientId) {
    return null;
  }

  @Override
  public ResponseEntity<List<PatientDto>> getAllPatients() {
    return null;
  }

  @Override
  public ResponseEntity<List<TreatmentDto>> getAllTreatments() {
    return null;
  }

  @Override
  public ResponseEntity<PatientDto> getPatient(String patientId) {
    return null;
  }

  @Override
  public ResponseEntity<PrescriptionDto> getPrescription(String prescriptionId) {
    return null;
  }

  @Override
  public ResponseEntity<List<PrescriptionDto>> getPrescriptionsOf(String patientId) {
    return null;
  }

  @Override
  public ResponseEntity<EventDto> modEvent(String eventId, NewEventDto newEventDto) {
    return null;
  }

  @Override
  public ResponseEntity<PrescriptionDto> modifyPrescription(String prescriptionId, NewPrescriptionDto newPrescriptionDto) {
    return null;
  }
}
