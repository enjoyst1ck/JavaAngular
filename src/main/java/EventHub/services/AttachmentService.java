package EventHub.services;

import EventHub.helpers.NotFoundException;
import EventHub.models.Attachment;
import EventHub.repositories.AttachmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository repo;

    public Attachment getById(Integer id) {
        Attachment attachment = repo.findById(id).orElseThrow(
                () -> new NotFoundException("Object is not exist, id: " + id)
        );

        return attachment;
    }

    @Transactional
    public void deleteById(Integer id) {
        Attachment attachment = repo.findById(id).orElseThrow(
                () -> new NotFoundException("Object is not exist, id: " + id)
        );

        repo.deleteById(id);
    }
}
