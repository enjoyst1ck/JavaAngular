package EventHub.controllers;

import EventHub.dtos.EventDto;
import EventHub.mappers.EventMapper;
import EventHub.models.Attachment;
import EventHub.models.Event;
import EventHub.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController extends GenericController<Event, EventDto, EventService> {

    @Autowired
    private EventService service;

    @GetMapping("/getfivelast")
    public List<EventDto> getFiveLast() {
        return service.getFiveLast();
    }

    @PostMapping("/addEvent")
    public List<EventDto> addEvent(@RequestBody Event object) {
        //service.insert(object);
        return service.insertEvent(object);
    }

    @PutMapping("/editEvent")
    public List<EventDto> editEvent(@RequestBody Event object) {
        return service.editEvent(object);
    }
}