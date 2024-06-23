package EventHub.repositories;

import EventHub.models.Artist;
import EventHub.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    @Query(value = "SELECT s FROM Staff s WHERE s.name = :staffName")
    public List<Artist> findByStaffName(@Param("staffName") String staffName);
}
