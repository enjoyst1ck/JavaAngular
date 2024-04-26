package EventHub.controllers;

import EventHub.models.Stuff;
import EventHub.services.StuffService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stuff")
@CrossOrigin(origins = "http://localhost:4200")
public class StuffController extends GenericController<Stuff, StuffService> {

}
