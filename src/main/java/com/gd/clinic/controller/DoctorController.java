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
  public ResponseEntity<Void> addPatient(final PatientDto patientDto) {
    return null;
  }

  @Override
  public ResponseEntity<Void> createPrescription(final PrescriptionDto prescriptionDto) {
    return null;
  }

  @Override
  public ResponseEntity<Void> deletePrescriptionById(final Long prescriptionId) {
    return null;
  }

  @Override
  public ResponseEntity<Void> dischargePatientByInsurance(final Long insuranceNumber) {
    return null;
  }

  @Override
  public ResponseEntity<List<EventDto>> getAllEvents() {
    return null;
  }

  @Override
  public ResponseEntity<Void> modifyEvent(final Long eventId, final EventDto eventDto) {
    return null;
  }

  @Override
  public ResponseEntity<PrescriptionDto> modifyPrescription(final Long prescriptionId,
      final String prescriptionParameter, final String parameterValue) {
    return null;
  }

}
