package travel.travel.model.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
public class TourResponseGetByID {
    private Long id;
    private String tourName;
    private String aboutTour;
    private int days;
    private int nights;
    private int price;
    private String pax;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Map<String, String> detailsOfTour;
    private String coordinatesImage;
    private List<String> tourImages;

    public TourResponseGetByID(Long id, String tourName, String aboutTour, int days, int nights, int price,
                               String pax, LocalDate dateFrom, LocalDate dateTo, Map<String, String> detailsOfTour,
                               String coordinatesImage,List<String> tourImages) {
        this.id = id;
        this.tourName = tourName;
        this.aboutTour = aboutTour;
        this.days = days;
        this.nights = nights;
        this.price = price;
        this.pax = pax;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.detailsOfTour = detailsOfTour;
        this.coordinatesImage = coordinatesImage;
        this.tourImages = tourImages;
    }
}
