package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Event;
import java.util.List;

public interface EventService {

    public List<Event> retrieveEvents();

    public Event getEvent(Long eventId);

    public Event saveEvent(Event event);

    public void deleteEvent(Long eventId);

    public void updateEvent(Event event);
}
