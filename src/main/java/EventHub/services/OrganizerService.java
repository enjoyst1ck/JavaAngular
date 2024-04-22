package EventHub.services;

import EventHub.models.Organizer;
import EventHub.repositories.OrganizerRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService extends GenericService<Organizer, OrganizerRepository> {
}
