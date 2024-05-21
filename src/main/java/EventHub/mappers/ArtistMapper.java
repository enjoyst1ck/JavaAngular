package EventHub.mappers;

import EventHub.dtos.ArtistDto;
import EventHub.models.Artist;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper implements IMapper<Artist, ArtistDto> {
    @Override
    public Artist toModel(ArtistDto artistDto) {
        Artist artist = new Artist();
        artist.setId(artistDto.getId());
        artist.setName(artistDto.getName());
        artist.setAttachments(artistDto.getAttachments());
        artist.setEvents(artistDto.getEvents());

        return artist;
    }

    @Override
    public ArtistDto toDto(Artist artist) {
        ArtistDto artistDto = new ArtistDto();
        artistDto.setId(artist.getId());
        artistDto.setName(artist.getName());
        artistDto.setAttachments(artist.getAttachments());
        artistDto.setEvents(artist.getEvents());

        return artistDto;
    }
}
