package travel.travel.model.dto.response;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class TourResponseGetByID {
    private Long id;
    private String tourName;
    private String aboutTour;
    private int daysByCategory;
    private int nights;
    private int price;
    private int paxPrice;
    private String pax;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<TourDetailsResponse> tourDetailsResponse;
    private String coordinatesImage;
    private List<String> tourImages;
    private List<String> whatIsIncluded;
    private List<String> whatIsExcluded;
}
