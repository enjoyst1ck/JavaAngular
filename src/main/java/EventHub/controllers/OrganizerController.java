package EventHub.controllers;

import EventHub.models.Organizer;
import EventHub.services.OrganizerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("organizers")
@CrossOrigin(origins = "http://localhost:4200")
public class OrganizerController extends GenericController<Organizer, OrganizerService> {
}