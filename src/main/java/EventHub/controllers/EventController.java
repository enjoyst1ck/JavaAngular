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
    public List<EventDto> getFiveLast() throws Exception {
        return service.getFiveLast();
    }

    @PostMapping("/addEvent")
    public List<EventDto> addEvent(@RequestBody Event object) throws Exception {
        //service.insert(object);
        return service.insertEvent(object);
    }

    @PutMapping("/editEvent")
    public List<EventDto> editEvent(@RequestBody Event object) throws Exception {
        return service.editEvent(object);
    }
    @GetMapping("/search/{eventName}")
    public List<EventDto> findByEventName(@PathVariable String eventName) throws Exception {
        return service.findByEventName(eventName);
    }

    @GetMapping("/searchWithSort")
    public List<EventDto> searchWithSort(@RequestParam(name = "eventName", required = false) String eventName,
                                       @RequestParam(name = "sort", required = false, defaultValue = "start_date") String sort) throws Exception {
        return service.findWithSort(eventName, sort);
    }
}