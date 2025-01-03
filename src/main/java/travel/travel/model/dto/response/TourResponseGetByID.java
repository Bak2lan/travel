package travel.travel.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
@Data
@Builder
public class TourResponseGetByID {
    private Long id;
    private double latitude;
    private double longitude;
    private String tourName;
    private String aboutTour;
    private int days;
    private int nights;
    private int price;
    private int pax;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Map<String,String> detailsOfTour;

    public TourResponseGetByID(Long id, double latitude,
                               double longitude, String tourName,
                               String aboutTour, int days,
                               int nights, int price, int pax,
                               LocalDate dateFrom,
                               LocalDate dateTo, Map<String, String> detailsOfTour) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tourName = tourName;
        this.aboutTour = aboutTour;
        this.days = days;
        this.nights = nights;
        this.price = price;
        this.pax = pax;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.detailsOfTour = detailsOfTour;
    }

    public TourResponseGetByID() {

    }
}
