package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import travel.travel.model.Location;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tours")
public class Tour extends Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_seq")
    @SequenceGenerator(name = "tour_seq", sequenceName = "tour_sequence", initialValue = 6, allocationSize = 1)
    private Long id;
    private String tourName;
    private String aboutTour;
    private int days;
    private int nights;
    private int price;
    private int pax;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    @ManyToOne
    private Category category;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> images;

    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String, String> detailsOfTour;

    @ManyToOne(fetch = FetchType.LAZY)
    private Travel travel;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sight sight;

    public Tour(double latitude, double longitude, String tourName, String aboutTour, int days, int nights, int price, int pax, LocalDate dateFrom, LocalDate dateTo, Category category, List<String> images, Map<String, String> detailsOfTour, Travel travel, Sight sight) {
        super(latitude, longitude);
        this.tourName = tourName;
        this.aboutTour = aboutTour;
        this.days = days;
        this.nights = nights;
        this.price = price;
        this.pax = pax;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.category = category;
        this.images = images;
        this.detailsOfTour = detailsOfTour;
        this.travel = travel;
        this.sight = sight;
    }

    public Long getId() {
        return id;
    }

    public String getTourName() {
        return tourName;
    }

    public String getAboutTour() {
        return aboutTour;
    }

    public int getDays() {
        return days;
    }

    public int getNights() {
        return nights;
    }

    public int getPrice() {
        return price;
    }

    public int getPax() {
        return pax;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public Category getCategory() {
        return category;
    }

    public List<String> getImages() {
        return images;
    }

    public Map<String, String> getDetailsOfTour() {
        return detailsOfTour;
    }

    public Travel getTravel() {
        return travel;
    }

    public Sight getSight() {
        return sight;
    }
}
