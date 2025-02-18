package travel.travel.model.dto.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;
@Data
@Builder
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
    private String detailsOfTour;
    private String coordinatesImage;

    public TourResponseGetByID(Long id,String tourName,
                               String aboutTour, int days,
                               int nights, int price, String pax,
                               LocalDate dateFrom,
                               LocalDate dateTo, String detailsOfTour, String coordinatesImage) {
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
    }

    public TourResponseGetByID() {

    }
}
