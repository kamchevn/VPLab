package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.exceptions.EventNotFoundException;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("events", this.eventService.listAll());
        return "listEvents";
    }
    @GetMapping("/add-form")
    public String getAddEventsPage(Model model){
        model.addAttribute("locations",this.locationService.findAll());
        return "add-event";
    }
    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                           @RequestParam String description,
                           @RequestParam double popularityScore,
                           @RequestParam Long locationId, @RequestParam(required = false) Long eventId
    ){
        if(eventId==null){
            this.eventService.saveEvent(name,description,popularityScore,locationId);
        }
        else{
            this.eventService.editEvent(eventId,name,description,popularityScore,locationId);
        }
        return "redirect:/events";
    }
    @GetMapping("/delete-form/{id}")
    public String deleteEvent(@PathVariable Long id, Model model) {
        model.addAttribute("eventId",id);
        return "delete-form";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        this.eventService.deleteEvent(id);
        return "redirect:/events";
    }
    @GetMapping("/edit-form/{id}")
    public String getEditEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id).orElseThrow(() -> new EventNotFoundException(id));
        if (event == null) {
            return "redirect:/courses?error=Course%Not%Found";
        }
        model.addAttribute("event", event);
        model.addAttribute("locations", locationService.findAll());
        return "add-event";
    }
    @GetMapping("/like/{id}")
    public String likeEvent(@PathVariable Long id, Model model){
        this.eventService.like(id);
        model.addAttribute("events", this.eventService.listAll());
        return "listEvents";
    }
    @PostMapping("/search")
    public String searchEvents(@RequestParam(required = false) String eventName, @RequestParam(required = false) String popularityScore, @RequestParam(required = false) String locationId, Model model){
        List<Event> events = new ArrayList<>();
        if(!eventName.isEmpty() && popularityScore.isEmpty() && locationId == null){
            events = this.eventService.searchEvents(eventName);
        }
        else if(eventName.isEmpty() && !popularityScore.isEmpty() && locationId.isEmpty()){
            events = this.eventService.searchRatings(Double.parseDouble(popularityScore));
        }
        else if(eventName.isEmpty() && popularityScore.isEmpty() && !locationId.isEmpty()){
            events = this.eventService.searchByLocationId(Long.parseLong(locationId));
        }
        model.addAttribute("events", events);
        return "listEvents";
    }
}
