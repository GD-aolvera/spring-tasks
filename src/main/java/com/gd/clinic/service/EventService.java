package com.gd.clinic.service;

import com.gd.clinic.entity.Event;
import com.gd.clinic.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public Optional<Event> getById(UUID id){
        return eventRepository.findById(id);
    }

    public boolean existsById(UUID id) {
        return eventRepository.findById(id) != null;
    }

    public void save(Event event){
        eventRepository.save(event);
    }
}
