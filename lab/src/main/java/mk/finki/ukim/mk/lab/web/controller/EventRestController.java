package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventRestController {
    private final EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAll(){
        return eventService.listAll();
    }
    @GetMapping("/search/test")
    public List<Event> getAllByName(){
        return eventService.searchEvents("J-Fest");
    }
}
