package travel.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.travel.model.entity.Travel;
@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
}
