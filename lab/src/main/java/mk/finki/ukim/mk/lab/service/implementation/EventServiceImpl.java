package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.boostrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.exceptions.EventNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.LocationNotFoundException;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> searchEvents(String eventName) {
        return eventRepository.findAllByName(eventName);
    }

    @Override
    public List<Event> searchRatings(Double popularityScore) {
        return eventRepository.findAllByPopularityScore(popularityScore);
    }

    @Override
    public List<Event> searchByLocationId(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }

    @Override
    public Optional<Event> saveEvent(String name, String description, Double popularityScore, Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        Event event = new Event(name,description,popularityScore,location);
        return Optional.of(this.eventRepository.save(event));
    }

    @Override
    public Optional<Event> editEvent(Long eventId, String name, String description, Double popularityScore, Long locationId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        event.setName(name);
        event.setDescription(description);
        event.setLocation(locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId)));
        return Optional.of(this.eventRepository.save(event));
    }

    @Override
    public void deleteEvent(Long id) {
        this.eventRepository.deleteById(id);
    }

    @Override
    public void like(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        double popularity = event.getPopularityScore();
        popularity+=0.5;
        event.setPopularityScore(popularity);
        this.eventRepository.save(event);
    }
}
