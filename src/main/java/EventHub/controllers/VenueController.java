package EventHub.controllers;

import EventHub.models.Venue;
import EventHub.services.VenueService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("venues")
@CrossOrigin(origins = "http://localhost:4200")
public class VenueController extends GenericController<Venue, VenueService> {
}