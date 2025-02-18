package travel.travel.model.dto.response;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record TourGetAllResponse(
        Long id,
        String tourName,
        String image,
        String aboutTour,
        int days,
        int nights,
        int price,
        String pax,
        LocalDate dateFrom,
        LocalDate dateTo
) {
}
