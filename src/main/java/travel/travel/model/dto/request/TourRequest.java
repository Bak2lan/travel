package travel.travel.model.dto.request;

import java.time.LocalDate;
import java.util.List;

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
        List<String> tourDetails,
        String valueCategory
) {
}