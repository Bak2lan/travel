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
    @Query("SELECT new travel.travel.model.dto.response.TourResponseForPagination(t.id, t.tourName, c.dayTour, t.aboutTour, t.days, t.nights, t.price, t.pax, t.dateFrom, t.dateTo) " +
            "FROM Tour t LEFT JOIN t.category c " +
            "ORDER BY c.dayTour ASC")
    Page<TourResponseForPagination> getAllTour(Pageable pageable);

    @Query("SELECT new travel.travel.model.dto.response.TourResponseForPagination(t.id, t.tourName, c.dayTour, t.aboutTour, t.days, t.nights, t.price, t.pax, t.dateFrom, t.dateTo) " +
            "FROM Tour t LEFT JOIN t.category c " +
            "WHERE t.popular = true " +
            "ORDER BY c.dayTour ASC")
    Page<TourResponseForPagination> getAllTourByPopular(Pageable pageable);
}