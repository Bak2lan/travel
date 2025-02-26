package travel.travel.model.dto.request;

import java.time.LocalDate;
import java.util.List;

public record TourRequest(
        String tourName,
        String aboutTour,
        int daysByCategory,
        int nights,
        int price,
        String pax,
        LocalDate dateFrom,
        LocalDate dateTo,
        List<String> images,
        boolean popular,
        String coordinatesImage,
        List<TourDetailsRequest> tourDetailsRequest,
        List<String> whatIsIncluded,
        List<String> whatIsExcluded) {
}