package travel.travel.repository.JDBCTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import travel.travel.model.dto.response.TourDetailsResponse;
import travel.travel.model.dto.response.TourGetAllResponse;
import travel.travel.model.dto.response.TourResponseGetByID;
import java.time.LocalDate;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class TourJDBCTemplate {

    private final JdbcTemplate jdbcTemplate;

    public TourResponseGetByID getTourById(Long id) {
        String tourSql = """
        SELECT t.id, t.tour_name, t.about_tour, t.days_by_category, t.nights, t.price,
               t.date_from, t.date_to, t.popular, t.coordinates_image,
               STRING_AGG(ti.images, ', ') AS images
        FROM tours t
        LEFT JOIN tour_images ti ON t.id = ti.tour_id
        WHERE t.id = ?
        GROUP BY t.id;
    """;

        String paxAndPriceSql = """
        SELECT pax_and_price, pax_and_price_key FROM tour_pax_and_price WHERE tour_id = ?;
    """;

        String tourDetailsSql = """
        SELECT td.id, td.tours_detail_name, td.day, td.about_tour_details,
               STRING_AGG(tdi.image_tour_details, ', ') AS images
        FROM tours_details td
        LEFT JOIN tour_details_image_tour_details tdi ON td.id = tdi.tour_details_id
        LEFT JOIN public.tours t on td.tour_id = t.id
        WHERE t.id = ?
        GROUP BY td.id
        ORDER BY td.day;
    """;

        String includedSql = """
        SELECT wi.what_is_included FROM tour_what_is_included wi WHERE wi.tour_id = ?;
    """;

        String excludedSql = """
        SELECT we.what_is_excluded FROM tour_what_is_excluded we WHERE we.tour_id = ?;
    """;

        System.out.println("Executing tour query with id: " + id);

        List<TourResponseGetByID> tours = jdbcTemplate.query(tourSql, new Object[]{id}, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");
            String tourName = rs.getString("tour_name");
            String aboutTour = rs.getString("about_tour");
            int daysByCategory = rs.getInt("days_by_category");
            int nights = rs.getInt("nights");
            int price = rs.getInt("price");
            LocalDate dateFrom = rs.getDate("date_from").toLocalDate();
            LocalDate dateTo = rs.getDate("date_to").toLocalDate();
            boolean isPopular = rs.getBoolean("popular");
            String coordinatesImage = rs.getString("coordinates_image");
            String imagesStr = rs.getString("images");
            List<String> imagesList = imagesStr != null ? Arrays.asList(imagesStr.split(", ")) : new ArrayList<>();

            System.out.println("Tour found: " + tourName);

            System.out.println("Executing paxAndPrice query for tour_id: " + tourId);
            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (rs1) -> {
                paxAndPrice.put(rs1.getString("pax_and_price_key"), rs1.getInt("pax_and_price"));
            });

            System.out.println("Executing details query for tour_id: " + tourId);
            List<TourDetailsResponse> tourDetailsResponse = jdbcTemplate.query(tourDetailsSql, new Object[]{tourId}, (rs1, rowNum1) -> {
                Long detailsId = rs1.getLong("id");
                String detailsName = rs1.getString("tours_detail_name");
                String detailsDay = rs1.getString("day");
                String detailsAbout = rs1.getString("about_tour_details");
                String detailsImagesStr = rs1.getString("images");
                List<String> detailsImages = detailsImagesStr != null ? Arrays.asList(detailsImagesStr.split(", ")) : new ArrayList<>();

                return new TourDetailsResponse(detailsId, detailsName, detailsDay, detailsAbout, detailsImages);
            });

            List<String> whatIsIncludedList = jdbcTemplate.queryForList(includedSql, new Object[]{tourId}, String.class);
            List<String> whatIsExcludedList = jdbcTemplate.queryForList(excludedSql, new Object[]{tourId}, String.class);

            System.out.println("Fetched whatIsIncluded: " + whatIsIncludedList);
            System.out.println("Fetched whatIsExcluded: " + whatIsExcludedList);

            return new TourResponseGetByID(
                    tourId, tourName, aboutTour, daysByCategory, nights, price, paxAndPrice,
                    dateFrom, dateTo, isPopular, tourDetailsResponse, coordinatesImage, imagesList,
                    whatIsIncludedList, whatIsExcludedList
            );
        });

        return tours.isEmpty() ? null : tours.get(0);
    }

    public List<TourGetAllResponse> getAllTour() {
        String sql = """
        SELECT t.id, t.tour_name, t.about_tour, t.days_by_category, 
               t.nights, t.price, t.date_from, t.date_to,
               (
                   SELECT ti.images
                   FROM tour_images ti
                   WHERE ti.tour_id = t.id
                   LIMIT 1  
               ) AS image  
        FROM tours t
        ORDER BY t.days_by_category ASC;
    """;

        String paxAndPriceSql = """
        SELECT pax_and_price_key, pax_and_price FROM tour_pax_and_price WHERE tour_id = ?;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");

            // Загружаем paxAndPrice для текущего тура
            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (paxRs) -> {
                paxAndPrice.put(paxRs.getString("pax_and_price_key"), paxRs.getInt("pax_and_price"));
            });

            return new TourGetAllResponse(
                    tourId,
                    rs.getString("tour_name"),
                    rs.getString("about_tour"),
                    rs.getInt("days_by_category"),
                    rs.getInt("nights"),
                    rs.getInt("price"),
                    paxAndPrice,
                    rs.getObject("date_from", LocalDate.class),
                    rs.getObject("date_to", LocalDate.class),
                    rs.getString("image")
            );
        });
    }

    public List<TourGetAllResponse> getAllTourByPopular() {
        String sql = """
        SELECT t.id, t.tour_name, t.about_tour, t.days_by_category, 
               t.nights, t.price, t.date_from, t.date_to,
               (
                   SELECT ti.images
                   FROM tour_images ti
                   WHERE ti.tour_id = t.id
                   LIMIT 1  
               ) AS image  
        FROM tours t
        LEFT JOIN tour_images ti ON t.id = ti.tour_id
        WHERE t.popular = true
        GROUP BY t.id
        ORDER BY t.days_by_category ASC;
    """;
        String paxAndPriceSql = """
        SELECT pax_and_price_key, pax_and_price FROM tour_pax_and_price WHERE tour_id = ?;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");
        Map<String, Integer> paxAndPrice = new HashMap<>();
        jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (paxRs) -> {
            paxAndPrice.put(paxRs.getString("pax_and_price_key"), paxRs.getInt("pax_and_price"));
        });

        return new TourGetAllResponse(
                rs.getLong("id"),
                rs.getString("tour_name"),
                rs.getString("about_tour"),
                rs.getInt("days_by_category"),
                rs.getInt("nights"),
                rs.getInt("price"),
                paxAndPrice,
                rs.getObject("date_from", LocalDate.class),
                rs.getObject("date_to", LocalDate.class),
                rs.getString("image")
        );
        });
    }}