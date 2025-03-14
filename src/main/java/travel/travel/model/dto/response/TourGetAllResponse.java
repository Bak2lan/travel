package travel.travel.model.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Map;

@Builder
public record TourGetAllResponse(
        Long id,
        String tourName,
        String aboutTour,
        int days,
        int nights,
        int price,
        Map<String, Integer> paxAndPrice,
        LocalDate dateFrom,
        LocalDate dateTo,
        String image
) {
}