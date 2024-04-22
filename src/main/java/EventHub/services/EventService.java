package EventHub.services;

import EventHub.models.Event;
import EventHub.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService extends GenericService<Event, EventRepository> {
    @Autowired
    private EventRepository repo;
    public List<Event> getFiveLast() {
        return repo.getFiveLast();
    }
}
