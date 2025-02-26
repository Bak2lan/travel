package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.List;

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
    private String pax;
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

}