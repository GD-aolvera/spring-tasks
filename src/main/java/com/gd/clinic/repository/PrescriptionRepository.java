package com.gd.clinic.repository;

import com.gd.clinic.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {

    Optional<Prescription> findOneByPatientId(UUID id);

}