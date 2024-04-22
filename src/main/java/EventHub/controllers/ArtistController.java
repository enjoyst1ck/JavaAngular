package EventHub.controllers;

import EventHub.models.Artist;
import EventHub.services.ArtistService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("artists")
@CrossOrigin(origins = "http://localhost:4200")
public class ArtistController extends GenericController<Artist, ArtistService> {
}