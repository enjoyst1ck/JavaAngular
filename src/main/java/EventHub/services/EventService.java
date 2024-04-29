package EventHub.services;

import EventHub.models.Event;
import EventHub.models.Venue;
import EventHub.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService extends GenericService<Event, EventRepository> {
    @Autowired
    private EventRepository repo;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private StuffRepository stuffRepository;
    @Autowired
    private VenueRepository venueRepository;
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

        if(attachments != null) attachmentRepository.saveAll(attachments);

        return getAll();
    }

    public boolean editEvent(Event event) {
        var attachments = event.getAttachments();

        var eventFromDatabase = repo.findById(event.getId());
        var attachmentsFromDatabase = eventFromDatabase.get().getAttachments();

        var newAttachments = event.getAttachments();

        attachmentsFromDatabase.removeAll(newAttachments);

        if (attachmentsFromDatabase != null) {
            attachmentsFromDatabase.forEach(a -> {
                a.setEvent(null);
                attachmentRepository.delete(a);
            });
        }

        //event.setAttachments(null);
        var newEvent = repo.save(event);

        if(attachments != null) {
            attachments.forEach(f -> {
                f.setEvent(newEvent);
            });
        }

        if(attachments != null) attachmentRepository.saveAll(attachments);

        if (true) {

        }

        return true;
    }
}
