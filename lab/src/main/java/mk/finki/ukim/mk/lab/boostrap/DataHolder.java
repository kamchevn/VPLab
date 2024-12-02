package mk.finki.ukim.mk.lab.boostrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = null;
    public static List<Location> locations = null;

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public DataHolder(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void init() {
        locations = new ArrayList<>();
        if(this.locationRepository.count() == 0){
            locations.add(new Location("Location1", "Leninova 2", "500", "akaiB-Festwtjaowjtgja"));
            locations.add(new Location("Location2", "Leninova 3", "500","akaiB-Festwtjaowjtgja"));
            locations.add(new Location("Location3", "Leninova 4", "300","akaiB-Festwtjaowjtgja"));
            locations.add(new Location("Location4", "Leninova 5", "100","akaiB-Festwtjaowjtgja"));
            locations.add(new Location("Location5", "Leninova 6", "200","akaiB-Festwtjaowjtgja"));
            this.locationRepository.saveAll(locations);
        }

        events = new ArrayList<>();
        if(this.eventRepository.count() == 0){
            events.add(new Event("A-Fest", "oawA-Festfarwptwja", 4.00,locations.get(0)));
            events.add(new Event("B-Fest", "akaiB-Festwtjaowjtgja", 5.00,locations.get(1)));
            events.add(new Event("C-Fest", "aiptjaiwojtoptC-Festwja", 3.00,locations.get(2)));
            events.add(new Event("D-Fest", "aiptjojD-Festtaoiwtjtoptwja", 8.00,locations.get(3)));
            events.add(new Event("E-Fest", "okoiaojiogwojtoptE-Festwja", 5.00,locations.get(4)));
            events.add(new Event("F-Fest", "best fest ever - F-Fest", 10.00,locations.get(0)));
            events.add(new Event("G-Fest", "IOEJOFIwojG-Festtoptwja", 6.00,locations.get(1)));
            events.add(new Event("H-Fest", "41241aiH-Festwojtoptwja", 5.00,locations.get(2)));
            events.add(new Event("I-Fest", "aiptjaiwojtoptwja", 5.00,locations.get(3)));
            events.add(new Event("J-Fest", "oiawtoataiwojtoptwja", 3.00,locations.get(4)));
            this.eventRepository.saveAll(events);
        }
    }
}
