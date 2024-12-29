package travel.travel.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.model.entity.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query("select new travel.travel.model.dto.response.UserResponseForGetAll(u.id,u.name,u.email,u.password,u.phoneNumber ) from User u")
    List<UserResponse> getAllUsers(Pageable pageable);
}
