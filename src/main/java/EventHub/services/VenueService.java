package EventHub.services;

import EventHub.dtos.VenueDto;
import EventHub.mappers.VenueMapper;
import EventHub.models.Venue;
import EventHub.repositories.VenueRepository;
import org.springframework.stereotype.Service;

@Service
public class VenueService extends GenericService<Venue, VenueDto, VenueRepository, VenueMapper> {
}
