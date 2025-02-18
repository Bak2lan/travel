package travel.travel.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import travel.travel.model.dto.response.TourResponseForPagination;
import travel.travel.model.dto.response.TourResponseGetByID;
import travel.travel.model.entity.Tour;
import org.springframework.jdbc.core.JdbcTemplate;


@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {
    @Query("SELECT new travel.travel.model.dto.response.TourResponseForPagination(t.id, t.tourName, tc.dayTour, t.aboutTour, t.days, t.nights, t.price, t.pax, t.dateFrom, t.dateTo) " +
            "FROM Tour t JOIN t.category tc ORDER BY tc.day ASC")
    Page<TourResponseForPagination> getAllTour(Pageable pageable);

    }