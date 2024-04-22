package EventHub.repositories;

import EventHub.models.Artist;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}
