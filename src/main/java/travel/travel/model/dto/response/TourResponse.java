package travel.travel.model.dto.response;

import java.time.LocalDateTime;

public record TourResponse(
        String tourName,
        String aboutTour,
        int days,
        int nights,
        int price,
        int max,
        LocalDateTime dateFrom,
        LocalDateTime dateTo,
        Long sightId
) {
}
