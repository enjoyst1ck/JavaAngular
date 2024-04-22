package EventHub.repositories;

import EventHub.models.Venue;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {
}
