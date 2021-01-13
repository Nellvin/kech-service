package com.nellvin.kechservice.controler;

import com.nellvin.kechservice.model.Event;
import com.nellvin.kechservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class EventController {

    @Autowired
    private EventService eventService;

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @CrossOrigin
    @GetMapping("/api/events")
    public List<Event> getAllEvents() {
        return eventService.retrieveEvents();
    }

    @GetMapping("/api/events/{id}")
    public Event getEventById(@PathVariable(value = "id") Long eventId) {
        return eventService.getEvent(eventId);
    }

    @PostMapping("/api/events")
    public Event saveEvent(@RequestBody Event event) {
        Event eve = eventService.saveEvent(event);
        System.out.println("Event saved!");
        return eve;
    }

    @DeleteMapping("/api/events/{id}")
    public void deleteEvent(@PathVariable(name = "id") Long eventId) {
        eventService.deleteEvent(eventId);
        System.out.println("Event " + eventId + " has been deleted");
    }

    @PutMapping("/api/events/{id}")
    public void updateEvent(@RequestBody Event event, @PathVariable(name = "id") Long eventId) {
        Event eve = eventService.getEvent(eventId);
        if (eve != null)
            eventService.updateEvent(event);

    }

}
