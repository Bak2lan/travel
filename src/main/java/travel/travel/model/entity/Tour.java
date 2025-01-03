package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import travel.travel.model.Location;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tours")
public class Tour extends Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tourName;
    private String aboutTour;
    private int days;
    private int nights;
    private int price;
    private int max;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

     @ManyToOne
     private Category category;

    @ElementCollection
     private   List<String> images;

    @ElementCollection
     private   Map<String,String> detailsOfTour;

     @ManyToOne
     private Travel travel;

     @ManyToOne
     private Sight sight;

    public Tour(double latitude, double longitude, String tourName, String aboutTour, int days, int nights, int price, int max, LocalDateTime dateFrom, LocalDateTime dateTo, Category category, List<String> images, Map<String, String> detailsOfTour, Travel travel, Sight sight) {
        super(latitude, longitude);
        this.tourName = tourName;
        this.aboutTour = aboutTour;
        this.days = days;
        this.nights = nights;
        this.price = price;
        this.max = max;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.category = category;
        this.images = images;
        this.detailsOfTour = detailsOfTour;
        this.travel = travel;
        this.sight = sight;
    }
}
