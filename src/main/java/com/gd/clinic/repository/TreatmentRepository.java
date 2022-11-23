package com.gd.clinic.repository;

import com.gd.clinic.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TreatmentRepository extends JpaRepository<Treatment, UUID> {
}