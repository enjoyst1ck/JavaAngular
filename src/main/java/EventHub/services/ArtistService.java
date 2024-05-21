package EventHub.services;

import EventHub.dtos.ArtistDto;
import EventHub.mappers.ArtistMapper;
import EventHub.models.Artist;
import EventHub.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

@Service
public class ArtistService extends GenericService<Artist, ArtistDto, ArtistRepository, ArtistMapper> {
}
