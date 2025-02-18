package travel.travel.model.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TourResponseForPagination {
    private Long id;
    private String tourName;
    private String category;
    private String aboutTour;
    private int days;
    private int nights;
    private int price;
    private String pax;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public TourResponseForPagination(Long id, String tourName, String category, String aboutTour, int days, int nights, int price, String pax, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.tourName = tourName;
        this.category = category;
        this.aboutTour = aboutTour;
        this.days = days;
        this.nights = nights;
        this.price = price;
        this.pax = pax;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public TourResponseForPagination() {
    }
}
