package EventHub.controllers;

import EventHub.dtos.StaffDto;
import EventHub.models.Staff;
import EventHub.services.StaffService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("staff")
@CrossOrigin(origins = "http://localhost:4200")
public class StaffController extends GenericController<Staff, StaffDto, StaffService> {

}
