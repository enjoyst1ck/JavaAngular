package EventHub.services;

import EventHub.models.Attachment;
import EventHub.models.Event;
import EventHub.models.Venue;
import EventHub.repositories.*;
import jakarta.transaction.Transactional;
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
    private AttachmentService attachmentService;
    public List<Event> getFiveLast() {
        return repo.getFiveLast();
    }

    @Transactional
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

    @Transactional
    public List<Event> editEvent(Event event) {
        var attachments = event.getAttachments();

        var eventFromDatabase = repo.findById(event.getId());
        var attachmentsFromDatabase = eventFromDatabase.get().getAttachments();

        attachmentsFromDatabase.forEach(a -> {
            if (!attachments.contains(a)) {
                a.setEvent(null);
                attachmentRepository.delete(a);
            }
        });


        if(attachments != null) {
            attachments.forEach(f -> {
                if(!attachmentsFromDatabase.contains(f)) {
                    f.setEvent(event);
                }
            });
        }
//        if(attachments != null) attachmentRepository.saveAll(attachments);

        repo.save(event);

        if (true) {

        }

        return getAll();
    }
}
