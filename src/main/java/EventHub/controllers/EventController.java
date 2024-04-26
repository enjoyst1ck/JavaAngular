package EventHub.controllers;

import EventHub.models.Event;
import EventHub.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController extends GenericController<Event, EventService> {

    @Autowired
    private EventService service;

    @GetMapping("/getfivelast")
    public List<Event> getFiveLast() {
        return service.getFiveLast();
    }

    @PostMapping("/addEvent")
    public List<Event> addEvent(@RequestBody Event object) {
        //service.insert(object);
        return service.insertEvent(object);
    }

    @PutMapping("/editEvent")
    public Event editEvent(@RequestBody Event object) {
        //service.insert(object);
        return object;
    }
}