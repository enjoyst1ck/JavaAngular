package EventHub.services;

import EventHub.models.Event;
import EventHub.repositories.AttachmentRepository;
import EventHub.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService extends GenericService<Event, EventRepository> {
    @Autowired
    private EventRepository repo;
    @Autowired
    private AttachmentRepository attachmentRepository;
    public List<Event> getFiveLast() {
        return repo.getFiveLast();
    }

    public List<Event> insertEvent(Event event) {
        var attachments = event.getAttachments();

        //event.setAttachments(null);
        var newEvent = repo.save(event);

        if(attachments != null) {
            attachments.forEach(f -> {
              f.setEvent(newEvent);
            });
        }

        attachmentRepository.saveAll(attachments);
        return getAll();
    }
}
