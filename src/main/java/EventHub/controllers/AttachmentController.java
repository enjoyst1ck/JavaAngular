package EventHub.controllers;

import EventHub.models.Attachment;
import EventHub.services.AttachmentService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AttachmentController {
    @Autowired
    private AttachmentService service;

    @GetMapping("attachments/{id}")
    private Attachment getById(@PathVariable Integer id) {

        return service.getById(id);

        /*Attachment attachment = service.getById(id);
        Hibernate.initialize(attachment);
        return attachment;*/
    }
}