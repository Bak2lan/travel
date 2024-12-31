package travel.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.travel.model.entity.Tour;
@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {
}
