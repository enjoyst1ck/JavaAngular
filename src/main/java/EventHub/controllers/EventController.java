package EventHub.controllers;

import EventHub.models.Event;
import EventHub.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}