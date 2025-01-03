package travel.travel.model.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TourResponseForPagination {
    private Long id;
       private double latitude;
       private double longitude;
       private String tourName;
       private String category;
       private String aboutTour;
       private int days;
       private int nights;
       private int price;
       private int max;
       private LocalDateTime dateFrom;
       private LocalDateTime dateTo;

     public TourResponseForPagination(Long id,double latitude, double longitude, String tourName,String category, String aboutTour, int days, int nights, int price, int max, LocalDateTime dateFrom, LocalDateTime dateTo) {
         this.id=id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tourName = tourName;
        this.category=category;
        this.aboutTour = aboutTour;
        this.days = days;
        this.nights = nights;
        this.price = price;
        this.max = max;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public TourResponseForPagination() {
    }
}
