package com.gd.clinic.repository;

import com.gd.clinic.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Override
    Optional<Event> findById(UUID id);

}