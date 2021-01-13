package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Event;
import com.nellvin.kechservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> retrieveEvents() {
        List<Event> list = eventRepository.findAll();
        Date now = new Date();
        list.removeIf(event -> event.getDatetime().compareTo(now) <=0);
        Collections.sort(list, Comparator.comparing(Event::getDatetime));
        return list;
    }

    @Override
    public Event getEvent(Long eventId) {
        return eventRepository.findById(eventId).get();
    }

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public void updateEvent(Event event) {
        eventRepository.save(event);
    }
}
