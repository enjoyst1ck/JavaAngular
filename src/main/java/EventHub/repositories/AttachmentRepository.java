package EventHub.repositories;

import EventHub.models.Attachment;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
