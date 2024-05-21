package EventHub.mappers;

import EventHub.dtos.EventDto;
import EventHub.models.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper implements IMapper<Event, EventDto> {
    @Override
    public Event toModel(EventDto eventDto) {
        Event event = new Event();
        event.setId(eventDto.getId());
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setStartDate(eventDto.getStartDate());
        event.setEndDate(eventDto.getEndDate());
        event.setAttachments(eventDto.getAttachments());
        event.setReviews(eventDto.getReviews());
        event.setVenue(eventDto.getVenue());
        event.setArtists(eventDto.getArtists());
        event.setStaff(eventDto.getStaff());

        return event;
    }

    @Override
    public EventDto toDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setName(event.getName());
        eventDto.setDescription(event.getDescription());
        eventDto.setStartDate(event.getStartDate());
        eventDto.setEndDate(event.getEndDate());
        eventDto.setAttachments(event.getAttachments());
        eventDto.setReviews(event.getReviews());
        eventDto.setVenue(event.getVenue());
        eventDto.setArtists(event.getArtists());
        eventDto.setStaff(event.getStaff());

        return eventDto;
    }
}