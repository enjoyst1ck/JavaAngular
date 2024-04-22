package EventHub.services;

import EventHub.models.Artist;
import EventHub.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

@Service
public class ArtistService extends GenericService<Artist, ArtistRepository> {
}
