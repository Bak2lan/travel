package travel.travel.repository.JDBCTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import travel.travel.model.dto.response.TourResponseGetByID;
import java.time.LocalDate;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class TourJDBCTemplate {

    private final JdbcTemplate jdbcTemplate;

    public TourResponseGetByID getTourById(Long id) {
        String tourSql = """
        SELECT t.id, t.tour_name, t.about_tour, t.days, t.nights, t.price, t.pax,
               t.date_from, t.date_to, t.coordinates_image,
               STRING_AGG(ti.images, ', ') AS images
        FROM tours t
        LEFT JOIN tour_images ti ON t.id = ti.tour_id
        WHERE t.id = ?
        GROUP BY t.id;
    """;

        String detailsSql = """
        SELECT td.details_of_tour AS detailsOfTour, td.details_of_tour_key AS detailsOfTourKey
        FROM tour_details_of_tour td
        WHERE td.tour_id = ?;
    """;

        String includedSql = """
        SELECT wi.what_is_included
        FROM tour_what_is_included wi
        WHERE wi.tour_id = ?;
    """;

        String excludedSql = """
        SELECT we.what_is_excluded
        FROM tour_what_is_excluded we
        WHERE we.tour_id = ?;
    """;

        System.out.println("Executing tour query with id: " + id);

        List<TourResponseGetByID> tours = jdbcTemplate.query(tourSql, new Object[]{id}, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");
            String tourName = rs.getString("tour_name");
            String aboutTour = rs.getString("about_tour");
            int days = rs.getInt("days");
            int nights = rs.getInt("nights");
            int price = rs.getInt("price");
            String pax = rs.getString("pax");
            LocalDate dateFrom = rs.getDate("date_from").toLocalDate();
            LocalDate dateTo = rs.getDate("date_to").toLocalDate();
            String coordinatesImage = rs.getString("coordinates_image");
            String imagesStr = rs.getString("images");
            List<String> imagesList = imagesStr != null ? Arrays.asList(imagesStr.split(", ")) : new ArrayList<>();
            System.out.println("Tour found: " + tourName);

            System.out.println("Executing details query for tour_id: " + tourId);
            List<Map<String, Object>> detailsList = jdbcTemplate.queryForList(detailsSql, new Object[]{tourId});
            Map<String, String> detailsOfTour = new HashMap<>();
            for (Map<String, Object> row : detailsList) {
                String key = (String) row.get("detailsOfTourKey");
                String value = (String) row.get("detailsOfTour");
                detailsOfTour.put(key, value);
            }

            // Получаем данные для whatIsIncluded
            List<String> whatIsIncludedList = jdbcTemplate.queryForList(includedSql, new Object[]{tourId}, String.class);

            // Получаем данные для whatIsExcluded
            List<String> whatIsExcludedList = jdbcTemplate.queryForList(excludedSql, new Object[]{tourId}, String.class);

            System.out.println("Fetched detailsOfTour: " + detailsOfTour);
            System.out.println("Fetched whatIsIncluded: " + whatIsIncludedList);
            System.out.println("Fetched whatIsExcluded: " + whatIsExcludedList);

            return new TourResponseGetByID(tourId, tourName, aboutTour, days, nights, price, pax, dateFrom, dateTo, detailsOfTour, coordinatesImage, imagesList, whatIsIncludedList, whatIsExcludedList);
        });

        return tours.isEmpty() ? null : tours.get(0);
    }
}