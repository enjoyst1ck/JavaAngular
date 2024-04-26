package EventHub.services;

import EventHub.models.Stuff;
import EventHub.repositories.StuffRepository;
import org.springframework.stereotype.Service;

@Service
public class StuffService extends GenericService<Stuff, StuffRepository> {
}
