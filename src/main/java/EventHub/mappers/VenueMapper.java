package EventHub.mappers;

import EventHub.dtos.VenueDto;
import EventHub.models.Venue;
import org.springframework.stereotype.Component;

@Component
public class VenueMapper implements IMapper<Venue, VenueDto> {
    @Override
    public Venue toModel(VenueDto venueDto) {
        Venue venue = new Venue();
        venue.setId(venueDto.getId());
        venue.setName(venueDto.getName());
        venue.setDescription(venueDto.getDescription());
        venue.setAddress(venueDto.getAddress());
        venue.setCity(venueDto.getCity());
        venue.setEvents(venueDto.getEvents());

        return venue;
    }

    @Override
    public VenueDto toDto(Venue venue) {
        VenueDto venueDto = new VenueDto();
        venueDto.setId(venue.getId());
        venueDto.setName(venue.getName());
        venueDto.setDescription(venue.getDescription());
        venueDto.setAddress(venue.getAddress());
        venueDto.setCity(venue.getCity());
        venueDto.setEvents(venue.getEvents());

        return venueDto;
    }
}