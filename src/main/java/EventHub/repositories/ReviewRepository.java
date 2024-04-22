package EventHub.repositories;

import EventHub.models.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query(value = "SELECT * FROM event_hub.review WHERE user_id = ?1", nativeQuery = true)
    public List<Review> GetSixLast(Integer userId);
}
