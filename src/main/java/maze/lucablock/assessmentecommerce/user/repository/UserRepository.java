package maze.lucablock.assessmentecommerce.user.repository;


import java.util.Optional;
import maze.lucablock.assessmentecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUsername(String email);

  Optional<Object> findDistinctByUsername(String username);

}