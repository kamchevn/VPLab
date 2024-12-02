package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        String stringNum = Integer.toString(numberOfTickets);
        Long noTickets = Long.parseLong(stringNum);
        return new EventBooking(eventName,attendeeName,attendeeAddress,noTickets);
    }
}
