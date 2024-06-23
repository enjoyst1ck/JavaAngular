package EventHub.repositories;

import EventHub.models.Artist;
import EventHub.models.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    @Query(value = "SELECT a FROM Artist a WHERE a.name = :artistName")
    public List<Artist> findByArtistName(@Param("artistName") String artistName);
}
