package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/event-booking")
public class EventBookingController {
    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }
    @PostMapping
    public String createBooking(@RequestParam String numTickets, @RequestParam String festival){
        return "redirect:/event-booking/print?festival=" + festival + "&numTickets=" + numTickets;
    }
    @GetMapping("/print")
    public String printBooking(@RequestParam String festival, @RequestParam String numTickets, Model model){
        int number = Integer.parseInt(numTickets);
        String attendeeName = "Petre Petreski";
        String attendeeAddress = "0.0.0.1";
        EventBooking booking = eventBookingService.placeBooking(festival,attendeeName,attendeeAddress,number);
        model.addAttribute("booking",booking);
        return "bookingConfirmation";
    }
}

