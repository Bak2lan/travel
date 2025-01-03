package travel.travel.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record TourRequest(
        double latitude,
        double longitude,
        String tourName,
        String aboutTour,
        int days,
        int nights,
        int price,
        int pax,
        LocalDate dateFrom,
        LocalDate dateTo,
        List<String> images,
        @Schema(description = "Details of tour",
                example = "{\"Day 1\": \"place\", \"Day 2\": \"place\", \"Day 3\": \"place\", \"Day 4\": \"place\", \"Day 5\": \"place\", \"Day 6\": \"place\"}")
        Map<String,String> tourDetails,
        Long sightId,
        Long categoryId
) {
}
