package travel.travel.model.dto.request;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record TourRequest(
        String tourName,
        String aboutTour,
        int daysByCategory,
        int nights,
        int price,
        String pax,
        Map<String, Integer> paxAndPrice,
        LocalDate dateFrom,
        LocalDate dateTo,
        List<String> images,
        boolean popular,
        String coordinatesImage,
        List<TourDetailsRequest> tourDetailsRequest,
        List<String> whatIsIncluded,
        List<String> whatIsExcluded) {
}