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

// ENGLISH
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
            LocalDate dateFrom = rs.getDate("date_from") != null ? rs.getDate("date_from").toLocalDate() : null;
            LocalDate dateTo = rs.getDate("date_to") != null ? rs.getDate("date_to").toLocalDate() : null;
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
        WHERE t.tour_name IS NOT NULL AND t.tour_name != ''
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
    }

    // RUSSIAN
    public TourResponseGetByID getTourByIdRu(Long id) {
        String tourSql = """
        SELECT t.id, t.tour_name_ru, t.about_tour_ru, t.days_by_category_ru, t.nights_ru, t.price_ru,
               t.date_from_ru, t.date_to_ru, t.popular_ru, t.coordinates_image_ru,
               STRING_AGG(ti.images_ru, ', ') AS images
        FROM tours t
        LEFT JOIN tour_images_ru ti ON t.id = ti.tour_id
        WHERE t.id = ?
        GROUP BY t.id;
    """;

        String paxAndPriceSql = """
        SELECT pax_and_price_ru, pax_and_price_ru_key FROM tour_pax_and_price_ru WHERE tour_id = ?;
    """;

        String tourDetailsSql = """
        SELECT td.id, td.tours_detail_name_ru, td.day_ru, td.about_tour_details_ru,
               STRING_AGG(tdi.image_tour_details_ru, ', ') AS images
        FROM tours_details td
        LEFT JOIN tour_details_image_tour_details_ru tdi ON td.id = tdi.tour_details_id
        LEFT JOIN public.tours t on td.tour_id = t.id
        WHERE t.id = ?
        GROUP BY td.id
        ORDER BY td.day_ru;
    """;

        String includedSql = """
        SELECT wi.what_is_included_ru FROM tour_what_is_included_ru wi WHERE wi.tour_id = ?;
    """;

        String excludedSql = """
        SELECT we.what_is_excluded_ru FROM tour_what_is_excluded_ru we WHERE we.tour_id = ?;
    """;

        System.out.println("Executing tour query with id: " + id);

        List<TourResponseGetByID> tours = jdbcTemplate.query(tourSql, new Object[]{id}, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");
            String tourName = rs.getString("tour_name_ru");
            String aboutTour = rs.getString("about_tour_ru");
            int daysByCategory = rs.getInt("days_by_category_ru");
            int nights = rs.getInt("nights_ru");
            int price = rs.getInt("price_ru");
            LocalDate dateFrom = rs.getDate("date_from_ru") != null ? rs.getDate("date_from_ru").toLocalDate() : null;
            LocalDate dateTo = rs.getDate("date_to_ru") != null ? rs.getDate("date_to_ru").toLocalDate() : null;
            boolean isPopular = rs.getBoolean("popular_ru");
            String coordinatesImage = rs.getString("coordinates_image_ru");
            String imagesStr = rs.getString("images");
            List<String> imagesList = imagesStr != null ? Arrays.asList(imagesStr.split(", ")) : new ArrayList<>();

            System.out.println("Tour found: " + tourName);

            System.out.println("Executing paxAndPrice query for tour_id: " + tourId);
            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (rs1) -> {
                paxAndPrice.put(rs1.getString("pax_and_price_ru_key"), rs1.getInt("pax_and_price_ru"));
            });

            System.out.println("Executing details query for tour_id: " + tourId);
            List<TourDetailsResponse> tourDetailsResponse = jdbcTemplate.query(tourDetailsSql, new Object[]{tourId}, (rs1, rowNum1) -> {
                Long detailsId = rs1.getLong("id");
                String detailsName = rs1.getString("tours_detail_name_ru");
                String detailsDay = rs1.getString("day_ru");
                String detailsAbout = rs1.getString("about_tour_details_ru");
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

    public List<TourGetAllResponse> getAllTourRu() {
        String sql = """
        SELECT t.id, t.tour_name_ru, t.about_tour_ru, t.days_by_category_ru, 
               t.nights_ru, t.price_ru, t.date_from_ru, t.date_to_ru,
               (
                   SELECT ti.images_ru
                   FROM tour_images_ru ti
                   WHERE ti.tour_id = t.id
                   LIMIT 1  
               ) AS image  
        FROM tours t
        WHERE t.tour_name_ru IS NOT NULL AND t.tour_name_ru != ''
        ORDER BY t.days_by_category_ru ASC;
    """;

        String paxAndPriceSql = """
        SELECT pax_and_price_ru, pax_and_price_ru_key FROM tour_pax_and_price_ru WHERE tour_id = ?;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");

            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (paxRs) -> {
                paxAndPrice.put(paxRs.getString("pax_and_price_ru_key"), paxRs.getInt("pax_and_price_ru"));
            });

            return new TourGetAllResponse(
                    tourId,
                    rs.getString("tour_name_ru"),
                    rs.getString("about_tour_ru"),
                    rs.getInt("days_by_category_ru"),
                    rs.getInt("nights_ru"),
                    rs.getInt("price_ru"),
                    paxAndPrice,
                    rs.getObject("date_from_ru", LocalDate.class),
                    rs.getObject("date_to_ru", LocalDate.class),
                    rs.getString("image")
            );
        });
    }

    public List<TourGetAllResponse> getAllTourByPopularRu() {
        String sql = """
        SELECT t.id, t.tour_name_ru, t.about_tour_ru, t.days_by_category_ru, 
               t.nights_ru, t.price_ru, t.date_from_ru, t.date_to_ru,
               (
                   SELECT ti.images_ru
                   FROM tour_images_ru ti
                   WHERE ti.tour_id = t.id
                   LIMIT 1  
               ) AS image  
        FROM tours t
        LEFT JOIN tour_images_ru ti ON t.id = ti.tour_id
        WHERE t.popular_ru = true
        GROUP BY t.id
        ORDER BY t.days_by_category_ru ASC;
    """;
        String paxAndPriceSql = """
        SELECT pax_and_price_ru, pax_and_price_ru_key FROM tour_pax_and_price_ru WHERE tour_id = ?;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");
            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (paxRs) -> {
                paxAndPrice.put(paxRs.getString("pax_and_price_ru_key"), paxRs.getInt("pax_and_price_ru"));
            });

            return new TourGetAllResponse(
                    rs.getLong("id"),
                    rs.getString("tour_name_ru"),
                    rs.getString("about_tour_ru"),
                    rs.getInt("days_by_category_ru"),
                    rs.getInt("nights_ru"),
                    rs.getInt("price_ru"),
                    paxAndPrice,
                    rs.getObject("date_from_ru", LocalDate.class),
                    rs.getObject("date_to_ru", LocalDate.class),
                    rs.getString("image")
            );
        });
    }

    // GERMAN
    public TourResponseGetByID getTourByIdDe(Long id) {
        String tourSql = """
        SELECT t.id, t.tour_name_de, t.about_tour_de, t.days_by_category_de, t.nights_de, t.price_de,
               t.date_from_de, t.date_to_de, t.popular_de, t.coordinates_image_de,
               STRING_AGG(ti.images_de, ', ') AS images
        FROM tours t
        LEFT JOIN tour_images_de ti ON t.id = ti.tour_id
        WHERE t.id = ?
        GROUP BY t.id;
    """;

        String paxAndPriceSql = """
        SELECT pax_and_price_de, pax_and_price_de_key FROM tour_pax_and_price_de WHERE tour_id = ?;
    """;

        String tourDetailsSql = """
        SELECT td.id, td.tours_detail_name_de, td.day_de, td.about_tour_details_de,
               STRING_AGG(tdi.image_tour_details_de, ', ') AS images
        FROM tours_details td
        LEFT JOIN tour_details_image_tour_details_de tdi ON td.id = tdi.tour_details_id
        LEFT JOIN public.tours t on td.tour_id = t.id
        WHERE t.id = ?
        GROUP BY td.id
        ORDER BY td.day_de;
    """;

        String includedSql = """
        SELECT wi.what_is_included_de FROM tour_what_is_included_de wi WHERE wi.tour_id = ?;
    """;

        String excludedSql = """
        SELECT we.what_is_excluded_de FROM tour_what_is_excluded_de we WHERE we.tour_id = ?;
    """;

        System.out.println("Executing tour query with id: " + id);

        List<TourResponseGetByID> tours = jdbcTemplate.query(tourSql, new Object[]{id}, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");
            String tourName = rs.getString("tour_name_de");
            String aboutTour = rs.getString("about_tour_de");
            int daysByCategory = rs.getInt("days_by_category_de");
            int nights = rs.getInt("nights_de");
            int price = rs.getInt("price_de");
            LocalDate dateFrom = rs.getDate("date_from_de") != null ? rs.getDate("date_from_de").toLocalDate() : null;
            LocalDate dateTo = rs.getDate("date_to_de") != null ? rs.getDate("date_to_de").toLocalDate() : null;
            boolean isPopular = rs.getBoolean("popular_de");
            String coordinatesImage = rs.getString("coordinates_image_de");
            String imagesStr = rs.getString("images");
            List<String> imagesList = imagesStr != null ? Arrays.asList(imagesStr.split(", ")) : new ArrayList<>();

            System.out.println("Tour found: " + tourName);

            System.out.println("Executing paxAndPrice query for tour_id: " + tourId);
            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (rs1) -> {
                paxAndPrice.put(rs1.getString("pax_and_price_de_key"), rs1.getInt("pax_and_price_de"));
            });

            System.out.println("Executing details query for tour_id: " + tourId);
            List<TourDetailsResponse> tourDetailsResponse = jdbcTemplate.query(tourDetailsSql, new Object[]{tourId}, (rs1, rowNum1) -> {
                Long detailsId = rs1.getLong("id");
                String detailsName = rs1.getString("tours_detail_name_de");
                String detailsDay = rs1.getString("day_de");
                String detailsAbout = rs1.getString("about_tour_details_de");
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

    public List<TourGetAllResponse> getAllTourDe() {
        String sql = """
        SELECT t.id, t.tour_name_de, t.about_tour_de, t.days_by_category_de, 
               t.nights_de, t.price_de, t.date_from_de, t.date_to_de,
               (
                   SELECT ti.images_de
                   FROM tour_images_de ti
                   WHERE ti.tour_id = t.id
                   LIMIT 1  
               ) AS image  
        FROM tours t
        WHERE t.tour_name_de IS NOT NULL AND t.tour_name_de != ''
        ORDER BY t.days_by_category_de ASC;
    """;

        String paxAndPriceSql = """
        SELECT pax_and_price_de, pax_and_price_de_key FROM tour_pax_and_price_de WHERE tour_id = ?;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");

            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (paxRs) -> {
                paxAndPrice.put(paxRs.getString("pax_and_price_de_key"), paxRs.getInt("pax_and_price_de"));
            });

            return new TourGetAllResponse(
                    tourId,
                    rs.getString("tour_name_de"),
                    rs.getString("about_tour_de"),
                    rs.getInt("days_by_category_de"),
                    rs.getInt("nights_de"),
                    rs.getInt("price_de"),
                    paxAndPrice,
                    rs.getObject("date_from_de", LocalDate.class),
                    rs.getObject("date_to_de", LocalDate.class),
                    rs.getString("image")
            );
        });
    }

    public List<TourGetAllResponse> getAllTourByPopularDe() {
        String sql = """
        SELECT t.id, t.tour_name_de, t.about_tour_de, t.days_by_category_de, 
               t.nights_de, t.price_de, t.date_from_de, t.date_to_de,
               (
                   SELECT ti.images_de
                   FROM tour_images_de ti
                   WHERE ti.tour_id = t.id
                   LIMIT 1  
               ) AS image  
        FROM tours t
        LEFT JOIN tour_images_de ti ON t.id = ti.tour_id
        WHERE t.popular_de = true
        GROUP BY t.id
        ORDER BY t.days_by_category_de ASC;
    """;
        String paxAndPriceSql = """
        SELECT pax_and_price_de, pax_and_price_de_key FROM tour_pax_and_price_de WHERE tour_id = ?;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");
            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (paxRs) -> {
                paxAndPrice.put(paxRs.getString("pax_and_price_de_key"), paxRs.getInt("pax_and_price_de"));
            });

            return new TourGetAllResponse(
                    rs.getLong("id"),
                    rs.getString("tour_name_de"),
                    rs.getString("about_tour_de"),
                    rs.getInt("days_by_category_de"),
                    rs.getInt("nights_de"),
                    rs.getInt("price_de"),
                    paxAndPrice,
                    rs.getObject("date_from_de", LocalDate.class),
                    rs.getObject("date_to_de", LocalDate.class),
                    rs.getString("image")
            );
        });
    }

    // FRENCH
    public TourResponseGetByID getTourByIdFr(Long id) {
        String tourSql = """
        SELECT t.id, t.tour_name_fr, t.about_tour_fr, t.days_by_category_fr, t.nights_fr, t.price_fr,
               t.date_from_fr, t.date_to_fr, t.popular_fr, t.coordinates_image_fr,
               STRING_AGG(ti.images_fr, ', ') AS images
        FROM tours t
        LEFT JOIN tour_images_fr ti ON t.id = ti.tour_id
        WHERE t.id = ?
        GROUP BY t.id;
    """;

        String paxAndPriceSql = """
        SELECT pax_and_price_fr, pax_and_price_fr_key FROM tour_pax_and_price_fr WHERE tour_id = ?;
    """;

        String tourDetailsSql = """
        SELECT td.id, td.tours_detail_name_fr, td.day_fr, td.about_tour_details_fr,
               STRING_AGG(tdi.image_tour_details_fr, ', ') AS images
        FROM tours_details td
        LEFT JOIN tour_details_image_tour_details_fr tdi ON td.id = tdi.tour_details_id
        LEFT JOIN public.tours t on td.tour_id = t.id
        WHERE t.id = ?
        GROUP BY td.id
        ORDER BY td.day_fr;
    """;

        String includedSql = """
        SELECT wi.what_is_included_fr FROM tour_what_is_included_fr wi WHERE wi.tour_id = ?;
    """;

        String excludedSql = """
        SELECT we.what_is_excluded_fr FROM tour_what_is_excluded_fr we WHERE we.tour_id = ?;
    """;

        System.out.println("Executing tour query with id: " + id);

        List<TourResponseGetByID> tours = jdbcTemplate.query(tourSql, new Object[]{id}, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");
            String tourName = rs.getString("tour_name_fr");
            String aboutTour = rs.getString("about_tour_fr");
            int daysByCategory = rs.getInt("days_by_category_fr");
            int nights = rs.getInt("nights_fr");
            int price = rs.getInt("price_fr");
            LocalDate dateFrom = rs.getDate("date_from_fr") != null ? rs.getDate("date_from_fr").toLocalDate() : null;
            LocalDate dateTo = rs.getDate("date_to_fr") != null ? rs.getDate("date_to_fr").toLocalDate() : null;
            boolean isPopular = rs.getBoolean("popular_fr");
            String coordinatesImage = rs.getString("coordinates_image_fr");
            String imagesStr = rs.getString("images");
            List<String> imagesList = imagesStr != null ? Arrays.asList(imagesStr.split(", ")) : new ArrayList<>();

            System.out.println("Tour found: " + tourName);

            System.out.println("Executing paxAndPrice query for tour_id: " + tourId);
            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (rs1) -> {
                paxAndPrice.put(rs1.getString("pax_and_price_fr_key"), rs1.getInt("pax_and_price_fr"));
            });

            System.out.println("Executing details query for tour_id: " + tourId);
            List<TourDetailsResponse> tourDetailsResponse = jdbcTemplate.query(tourDetailsSql, new Object[]{tourId}, (rs1, rowNum1) -> {
                Long detailsId = rs1.getLong("id");
                String detailsName = rs1.getString("tours_detail_name_fr");
                String detailsDay = rs1.getString("day_fr");
                String detailsAbout = rs1.getString("about_tour_details_fr");
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


    public List<TourGetAllResponse> getAllTourFr() {
        String sql = """
        SELECT t.id, t.tour_name_fr, t.about_tour_fr, t.days_by_category_fr, 
               t.nights_fr, t.price_fr, t.date_from_fr, t.date_to_fr,
               (
                   SELECT ti.images_fr
                   FROM tour_images_fr ti
                   WHERE ti.tour_id = t.id
                   LIMIT 1  
               ) AS image  
        FROM tours t
        WHERE t.tour_name_fr IS NOT NULL AND t.tour_name_fr != ''
        ORDER BY t.days_by_category_fr ASC;
    """;

        String paxAndPriceSql = """
        SELECT pax_and_price_fr, pax_and_price_fr_key FROM tour_pax_and_price_fr WHERE tour_id = ?;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");

            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (paxRs) -> {
                paxAndPrice.put(paxRs.getString("pax_and_price_fr_key"), paxRs.getInt("pax_and_price_fr"));
            });
            return new TourGetAllResponse(
                    tourId,
                    rs.getString("tour_name_fr"),
                    rs.getString("about_tour_fr"),
                    rs.getInt("days_by_category_fr"),
                    rs.getInt("nights_fr"),
                    rs.getInt("price_fr"),
                    paxAndPrice,
                    rs.getObject("date_from_fr", LocalDate.class),
                    rs.getObject("date_to_fr", LocalDate.class),
                    rs.getString("image")
            );
        });
    }

    public List<TourGetAllResponse> getAllTourByPopularFr() {
        String sql = """
    SELECT t.id, t.tour_name_fr, t.about_tour_fr, t.days_by_category_fr, 
           t.nights_fr, t.price_fr, t.date_from_fr, t.date_to_fr,
           (
               SELECT ti.images_fr
               FROM tour_images_fr ti
               WHERE ti.tour_id = t.id
               LIMIT 1  
           ) AS image  
    FROM tours t
    LEFT JOIN tour_images_fr ti ON t.id = ti.tour_id
    WHERE t.popular_fr = true
    GROUP BY t.id
    ORDER BY t.days_by_category_fr ASC;
    """;

        String paxAndPriceSql = """
    SELECT pax_and_price_fr, pax_and_price_fr_key FROM tour_pax_and_price_fr WHERE tour_id = ?;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");
            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (paxRs) -> {
                paxAndPrice.put(paxRs.getString("pax_and_price_fr_key"), paxRs.getInt("pax_and_price_fr"));
            });

            return new TourGetAllResponse(
                    rs.getLong("id"),
                    rs.getString("tour_name_fr"),
                    rs.getString("about_tour_fr"),
                    rs.getInt("days_by_category_fr"),
                    rs.getInt("nights_fr"),
                    rs.getInt("price_fr"),
                    paxAndPrice,
                    rs.getObject("date_from_fr", LocalDate.class),
                    rs.getObject("date_to_fr", LocalDate.class),
                    rs.getString("image")
            );
        });
    }

//SPAIN

    public TourResponseGetByID getTourByIdEs(Long id) {
        String tourSql = """
    SELECT t.id, t.tour_name_es, t.about_tour_es, t.days_by_category_es, t.nights_es, t.price_es,
           t.date_from_es, t.date_to_es, t.popular_es, t.coordinates_image_es,
           STRING_AGG(ti.images_es, ', ') AS images
    FROM tours t
    LEFT JOIN tour_images_es ti ON t.id = ti.tour_id
    WHERE t.id = ?
    GROUP BY t.id;
    """;

        String paxAndPriceSql = """
    SELECT pax_and_price_es, pax_and_price_es_key FROM tour_pax_and_price_es WHERE tour_id = ?;
    """;

        String tourDetailsSql = """
    SELECT td.id, td.tours_detail_name_es, td.day_es, td.about_tour_details_es,
           STRING_AGG(tdi.image_tour_details_es, ', ') AS images
    FROM tours_details td
    LEFT JOIN tour_details_image_tour_details_es tdi ON td.id = tdi.tour_details_id
    LEFT JOIN public.tours t on td.tour_id = t.id
    WHERE t.id = ?
    GROUP BY td.id
    ORDER BY td.day_es;
    """;

        String includedSql = """
    SELECT wi.what_is_included_es FROM tour_what_is_included_es wi WHERE wi.tour_id = ?;
    """;

        String excludedSql = """
    SELECT we.what_is_excluded_es FROM tour_what_is_excluded_es we WHERE we.tour_id = ?;
    """;

        System.out.println("Executing tour query with id: " + id);

        List<TourResponseGetByID> tours = jdbcTemplate.query(tourSql, new Object[]{id}, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");
            String tourName = rs.getString("tour_name_es");
            String aboutTour = rs.getString("about_tour_es");
            int daysByCategory = rs.getInt("days_by_category_es");
            int nights = rs.getInt("nights_es");
            int price = rs.getInt("price_es");
            LocalDate dateFrom = rs.getDate("date_from_es") != null ? rs.getDate("date_from_es").toLocalDate() : null;
            LocalDate dateTo = rs.getDate("date_to_es") != null ? rs.getDate("date_to_es").toLocalDate() : null;
            boolean isPopular = rs.getBoolean("popular_es");
            String coordinatesImage = rs.getString("coordinates_image_es");
            String imagesStr = rs.getString("images");
            List<String> imagesList = imagesStr != null ? Arrays.asList(imagesStr.split(", ")) : new ArrayList<>();

            System.out.println("Tour found: " + tourName);

            System.out.println("Executing paxAndPrice query for tour_id: " + tourId);
            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (rs1) -> {
                paxAndPrice.put(rs1.getString("pax_and_price_es_key"), rs1.getInt("pax_and_price_es"));
            });

            System.out.println("Executing details query for tour_id: " + tourId);
            List<TourDetailsResponse> tourDetailsResponse = jdbcTemplate.query(tourDetailsSql, new Object[]{tourId}, (rs1, rowNum1) -> {
                Long detailsId = rs1.getLong("id");
                String detailsName = rs1.getString("tours_detail_name_es");
                String detailsDay = rs1.getString("day_es");
                String detailsAbout = rs1.getString("about_tour_details_es");
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

    public List<TourGetAllResponse> getAllTourEs() {
        String sql = """
    SELECT t.id, t.tour_name_es, t.about_tour_es, t.days_by_category_es, 
           t.nights_es, t.price_es, t.date_from_es, t.date_to_es,
           (
               SELECT ti.images_es
               FROM tour_images_es ti
               WHERE ti.tour_id = t.id
               LIMIT 1  
           ) AS image  
    FROM tours t
    WHERE t.tour_name_es IS NOT NULL AND t.tour_name_es != ''
    ORDER BY t.days_by_category_es ASC;
    """;

        String paxAndPriceSql = """
    SELECT pax_and_price_es, pax_and_price_es_key FROM tour_pax_and_price_es WHERE tour_id = ?;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");

            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (paxRs) -> {
                paxAndPrice.put(paxRs.getString("pax_and_price_es_key"), paxRs.getInt("pax_and_price_es"));
            });

            return new TourGetAllResponse(
                    tourId,
                    rs.getString("tour_name_es"),
                    rs.getString("about_tour_es"),
                    rs.getInt("days_by_category_es"),
                    rs.getInt("nights_es"),
                    rs.getInt("price_es"),
                    paxAndPrice,
                    rs.getObject("date_from_es", LocalDate.class),
                    rs.getObject("date_to_es", LocalDate.class),
                    rs.getString("image")
            );
        });
    }

    public List<TourGetAllResponse> getAllTourByPopularEs() {
        String sql = """
    SELECT t.id, t.tour_name_es, t.about_tour_es, t.days_by_category_es, 
           t.nights_es, t.price_es, t.date_from_es, t.date_to_es,
           (
               SELECT ti.images_es
               FROM tour_images_es ti
               WHERE ti.tour_id = t.id
               LIMIT 1  
           ) AS image  
    FROM tours t
    LEFT JOIN tour_images_es ti ON t.id = ti.tour_id
    WHERE t.popular_es = true
    GROUP BY t.id
    ORDER BY t.days_by_category_es ASC;
    """;

        String paxAndPriceSql = """
    SELECT pax_and_price_es, pax_and_price_es_key FROM tour_pax_and_price_es WHERE tour_id = ?;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long tourId = rs.getLong("id");
            Map<String, Integer> paxAndPrice = new HashMap<>();
            jdbcTemplate.query(paxAndPriceSql, new Object[]{tourId}, (paxRs) -> {
                paxAndPrice.put(paxRs.getString("pax_and_price_es_key"), paxRs.getInt("pax_and_price_es"));
            });

            return new TourGetAllResponse(
                    rs.getLong("id"),
                    rs.getString("tour_name_es"),
                    rs.getString("about_tour_es"),
                    rs.getInt("days_by_category_es"),
                    rs.getInt("nights_es"),
                    rs.getInt("price_es"),
                    paxAndPrice,
                    rs.getObject("date_from_es", LocalDate.class),
                    rs.getObject("date_to_es", LocalDate.class),
                    rs.getString("image")
            );
        });
    }
}