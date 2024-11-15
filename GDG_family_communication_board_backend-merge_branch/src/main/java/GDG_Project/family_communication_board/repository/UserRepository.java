package GDG_Project.family_communication_board.repository;


import GDG_Project.family_communication_board.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByFamilyNickname(String familyNickname);
}
