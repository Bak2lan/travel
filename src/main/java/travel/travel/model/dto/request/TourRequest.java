package travel.travel.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record TourRequest(
        String tourName,
        String aboutTour,
        int days,
        int nights,
        int price,
        String pax,
        LocalDate dateFrom,
        LocalDate dateTo,
        List<String> images,
        boolean popular,
        String coordinatesImage,
        String tourDetails,
        String valueCategory
) {
}