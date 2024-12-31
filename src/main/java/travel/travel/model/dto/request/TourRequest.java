package travel.travel.model.dto.request;

import java.time.LocalDateTime;

public record TourRequest(
        String tourName,
        String aboutTour,
        int days,
        int nights,
        int price,
        int max,
        LocalDateTime dateFrom,
        LocalDateTime dateTo
) {
}
