package travel.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import travel.travel.model.entity.User;
import travel.travel.validation.email.EmailValidation;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    Optional<User> existsUserByEmail(@Param("email") String email);


    Optional<User> findByEmail(String email);

    boolean existsByEmail(@EmailValidation String email);

}
