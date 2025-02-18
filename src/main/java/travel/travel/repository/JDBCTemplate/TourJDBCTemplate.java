package travel.travel.repository.JDBCTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import travel.travel.model.dto.response.TourResponseGetByID;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TourJDBCTemplate {

    private final JdbcTemplate jdbcTemplate;

    public TourResponseGetByID getTourById(Long id) {
        String tourSql = "SELECT t.id, t.tour_name, t.about_tour, t.days, t.nights, t.price, t.pax, " +
                "t.date_from, t.date_to, t.coordinates_image " +
                "FROM tours t " +
                "WHERE t.id = ?";

        String detailsSql = "SELECT td.details_of_tour " +
                "FROM tour_details_of_tour td " +
                "WHERE td.tour_id = ?";

        return jdbcTemplate.queryForObject(tourSql, new Object[]{id}, new RowMapper<TourResponseGetByID>() {
            @Override
            public TourResponseGetByID mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                Long tourId = rs.getLong("id");
                String tourName = rs.getString("tour_name");
                String aboutTour = rs.getString("about_tour");
                int days = rs.getInt("days");
                int nights = rs.getInt("nights");
                int price = rs.getInt("price");
                String pax = rs.getString("pax");
                java.time.LocalDate dateFrom = rs.getDate("date_from").toLocalDate();
                java.time.LocalDate dateTo = rs.getDate("date_to").toLocalDate();
                String coordinatesImage = rs.getString("coordinates_image");

                List<String> details = jdbcTemplate.queryForList(detailsSql, new Object[]{tourId}, String.class);
                return new TourResponseGetByID(tourId, tourName, aboutTour, days, nights, price, pax, dateFrom, dateTo, details, coordinatesImage);
            }
        });
    }
}