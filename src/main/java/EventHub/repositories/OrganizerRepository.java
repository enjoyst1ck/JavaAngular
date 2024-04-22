package EventHub.repositories;

import EventHub.models.Organizer;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
}
