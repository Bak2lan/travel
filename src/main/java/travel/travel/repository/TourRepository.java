package travel.travel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travel.travel.model.dto.response.TourResponseForPagination;
import travel.travel.model.entity.Tour;
@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {
    @Query("select new travel.travel.model.dto.response.TourResponseForPagination(t.id,t.latitude, t.longitude, t.tourName,tc.dayTour, t.aboutTour, t.days, t.nights, t.price, t.max, t.dateFrom, t.dateTo) from Tour t join  t.category tc order by tc.day asc ")
    Page<TourResponseForPagination> getAllTour(Pageable pageable);
}
