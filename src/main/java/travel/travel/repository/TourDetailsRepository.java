package travel.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.travel.model.entity.TourDetails;

@Repository
public interface TourDetailsRepository extends JpaRepository<TourDetails, Long> {
}