package travel.travel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.travel.model.entity.Sight;
@Repository
public interface SightRepository extends JpaRepository<Sight,Long> {
}
