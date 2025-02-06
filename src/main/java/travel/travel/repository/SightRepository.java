package travel.travel.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travel.travel.model.dto.response.SightResponse;
import travel.travel.model.entity.Sight;

import java.util.List;

@Repository
public interface SightRepository extends JpaRepository<Sight,Long> {
    @Query("select new travel.travel.model.dto.response.SightResponse(s.id,s.images,s.nameOfSight,s.description) from Sight s ")
    List<SightResponse> getAllSights(Pageable pageable);


}
