package EventHub.repositories;

import EventHub.models.Event;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query(value = "SELECT * FROM event_hub.event ORDER BY start_date LIMIT 5", nativeQuery = true)
    public List<Event> getFiveLast();

    @Query(value = "SELECT e FROM Event e WHERE (:eventName IS NULL OR e.name = :eventName)")
    public List<Event> findByEventName(@Param("eventName") String eventName, Sort sort);
}
