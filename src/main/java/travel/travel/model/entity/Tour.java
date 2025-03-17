package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_seq")
    @SequenceGenerator(name = "tour_seq", sequenceName = "tour_sequence", initialValue = 13, allocationSize = 1)
    private Long id;
    private String tourName;
    @Column(length = 8000)
    private String aboutTour;
    private int daysByCategory;
    private int nights;
    private int price;
    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String, Integer> paxAndPrice;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> images;
    @ManyToOne(fetch = FetchType.LAZY)
    private Travel travel;
    @ManyToOne(fetch = FetchType.LAZY)
    private Sight sight;
    private boolean popular;
    private String coordinatesImage;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> whatIsIncluded;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> whatIsExcluded;
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TourDetails> tourDetails;

    public Tour(Long id, String tourName, String aboutTour, int daysByCategory, int nights, int price, Map<String, Integer> paxAndPrice, LocalDate dateFrom, LocalDate dateTo, List<String> images, Travel travel, Sight sight, boolean popular, String coordinatesImage, List<String> whatIsIncluded, List<String> whatIsExcluded, List<TourDetails> tourDetails) {
        this.id = id;
        this.tourName = tourName;
        this.aboutTour = aboutTour;
        this.daysByCategory = daysByCategory;
        this.nights = nights;
        this.price = price;
        this.paxAndPrice = paxAndPrice;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.images = images;
        this.travel = travel;
        this.sight = sight;
        this.popular = popular;
        this.coordinatesImage = coordinatesImage;
        this.whatIsIncluded = whatIsIncluded;
        this.whatIsExcluded = whatIsExcluded;
        this.tourDetails = tourDetails;
    }

    private String tourNameRu;
    @Column(length = 8000)
    private String aboutTourRu;
    private int daysByCategoryRu;
    private int nightsRu;
    private int priceRu;
    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String, Integer> paxAndPriceRu;
    private LocalDate dateFromRu;
    private LocalDate dateToRu;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> imagesRu;
    @ManyToOne(fetch = FetchType.LAZY)
    private Travel travelRu;
    @ManyToOne(fetch = FetchType.LAZY)
    private Sight sightRu;
    private boolean popularRu;
    private String coordinatesImageRu;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> whatIsIncludedRu;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> whatIsExcludedRu;

    private String tourNameFr;
    @Column(length = 8000)
    private String aboutTourFr;
    private int daysByCategoryFr;
    private int nightsFr;
    private int priceFr;
    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String, Integer> paxAndPriceFr;
    private LocalDate dateFromFr;
    private LocalDate dateToFr;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> imagesFr;
    @ManyToOne(fetch = FetchType.LAZY)
    private Travel travelFr;
    @ManyToOne(fetch = FetchType.LAZY)
    private Sight sightFr;
    private boolean popularFr;
    private String coordinatesImageFr;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> whatIsIncludedFr;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> whatIsExcludedFr;

    private String tourNameDe;
    @Column(length = 8000)
    private String aboutTourDe;
    private int daysByCategoryDe;
    private int nightsDe;
    private int priceDe;
    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String, Integer> paxAndPriceDe;
    private LocalDate dateFromDe;
    private LocalDate dateToDe;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> imagesDe;
    @ManyToOne(fetch = FetchType.LAZY)
    private Travel travelDe;
    @ManyToOne(fetch = FetchType.LAZY)
    private Sight sightDe;
    private boolean popularDe;
    private String coordinatesImageDe;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> whatIsIncludedDe;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> whatIsExcludedDe;

    private String tourNameEs;
    @Column(length = 8000)
    private String aboutTourEs;
    private int daysByCategoryEs;
    private int nightsEs;
    private int priceEs;
    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String, Integer> paxAndPriceEs;
    private LocalDate dateFromEs;
    private LocalDate dateToEs;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> imagesEs;
    @ManyToOne(fetch = FetchType.LAZY)
    private Travel travelEs;
    @ManyToOne(fetch = FetchType.LAZY)
    private Sight sightEs;
    private boolean popularEs;
    private String coordinatesImageEs;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> whatIsIncludedEs;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> whatIsExcludedEs;
}