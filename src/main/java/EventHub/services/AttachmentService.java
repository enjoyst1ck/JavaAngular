package EventHub.services;

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
        return repo.findById(id).get();
    }

    @Transactional
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
