package travel.travel.model.dto.response;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class TourResponseGetByID {
    private Long id;
    private String tourName;
    private String aboutTour;
    private int daysByCategory;
    private int nights;
    private int price;
    private String pax;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<TourDetailsResponse> tourDetailsResponse;
    private String coordinatesImage;
    private List<String> tourImages;
    private List<String> whatIsIncluded;
    private List<String> whatIsExcluded;

    public TourResponseGetByID(Long id, String tourName, String aboutTour, int daysByCategory, int nights, int price,
                               String pax, LocalDate dateFrom, LocalDate dateTo, List<TourDetailsResponse> tourDetailsResponse,
                               String coordinatesImage, List<String> tourImages, List<String> whatIsIncluded, List<String> whatIsExcluded) {
        this.id = id;
        this.tourName = tourName;
        this.aboutTour = aboutTour;
        this.daysByCategory = daysByCategory;
        this.nights = nights;
        this.price = price;
        this.pax = pax;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.tourDetailsResponse = tourDetailsResponse;
        this.coordinatesImage = coordinatesImage;
        this.tourImages = tourImages;
        this.whatIsIncluded = whatIsIncluded;
        this.whatIsExcluded = whatIsExcluded;
    }
}
