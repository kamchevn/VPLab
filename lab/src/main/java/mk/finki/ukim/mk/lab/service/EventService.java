package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    Optional<Event> getEventById(Long id);
    List<Event> searchEvents(String eventName);
    List<Event> searchRatings(Double popularityScore);
    List<Event> searchByLocationId(Long locationId);
    Optional<Event> saveEvent(String name, String description, Double popularityScore, Long locationId);
    Optional<Event> editEvent(Long eventId, String name, String description, Double popularityScore, Long locationId);
    void deleteEvent(Long id);
    void like(Long eventId);
}
