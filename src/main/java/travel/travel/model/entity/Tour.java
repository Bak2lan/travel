package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tours")
public class Tour{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_seq")
    @SequenceGenerator(name = "tour_seq", sequenceName = "tour_sequence", initialValue = 13, allocationSize = 1)
    private Long id;
    private String tourName;
    @Column(length = 8000)
    private String aboutTour;
    private int days;
    private int nights;
    private int price;
    private String pax;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    @ManyToOne
    private Category category;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> images;

    private String detailsOfTour;

    @ManyToOne(fetch = FetchType.LAZY)
    private Travel travel;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sight sight;
    private String valueCategory;
    private boolean popular;
    private String coordinatesImage;

    public Tour(String tourName, String aboutTour, int days, int nights, int price, String pax, LocalDate dateFrom, LocalDate dateTo, Category category, List<String> images, String detailsOfTour, Travel travel, Sight sight,boolean popular, String coordinatesImage) {
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
        this.popular = popular;
        this.coordinatesImage = coordinatesImage;
    }
    public Tour(String tourName, String aboutTour, int days, int nights, int price, String pax, LocalDate dateFrom, LocalDate dateTo, Category category, List<String> images, String detailsOfTour, Travel travel,String valueCategory, boolean popular, String coordinatesImage) {
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
        this.valueCategory = valueCategory;
        this.popular = popular;
        this.coordinatesImage = coordinatesImage;
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

    public String getPax() {
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

    public String getDetailsOfTour() {
        return detailsOfTour;
    }

    public Travel getTravel() {
        return travel;
    }

    public String getValueCategory() {
        return valueCategory;
    }
    public boolean isPopular() {
        return isPopular();
    }

    public String getCoordinatesImage() {
        return getCoordinatesImage();
    }
}