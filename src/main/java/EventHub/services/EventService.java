package EventHub.services;

import EventHub.dtos.EventDto;
import EventHub.mappers.EventMapper;
import EventHub.models.Event;
import EventHub.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService extends GenericService<Event, EventDto, EventRepository, EventMapper> {
    @Autowired
    private EventRepository repo;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private EventMapper mapper;

    public List<EventDto> getFiveLast() {
        var entity = repo.getFiveLast();
        return entity.stream().map(mapper::toDto).toList();
    }

    @Transactional
    public List<EventDto> insertEvent(Event event) {
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
    public List<EventDto> editEvent(Event event) {
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
