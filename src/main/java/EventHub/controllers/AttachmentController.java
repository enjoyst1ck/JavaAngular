package EventHub.controllers;

import EventHub.models.Attachment;
import EventHub.services.AttachmentService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AttachmentController {
    @Autowired
    private AttachmentService service;

    @GetMapping("attachments/{id}")
    private Attachment getById(@PathVariable Integer id) throws Exception {

        return service.getById(id);

        /*Attachment attachment = service.getById(id);
        Hibernate.initialize(attachment);
        return attachment;*/
    }

    @DeleteMapping("attachments/delete/{id}")
    private void delete(@PathVariable Integer id) throws Exception {

        service.deleteById(id);

        /*Attachment attachment = service.getById(id);
        Hibernate.initialize(attachment);
        return attachment;*/
    }
}