package EventHub.repositories;

import EventHub.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
