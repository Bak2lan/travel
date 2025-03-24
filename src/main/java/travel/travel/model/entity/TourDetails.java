package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tours_details")
public class TourDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tour_seq")
    @SequenceGenerator(name = "tour_seq", sequenceName = "tour_sequence", initialValue = 13, allocationSize = 1)
    private Long id;
    private String toursDetailName;
    private String day;
    @Column(length = 10000)
    private String aboutTourDetails;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String>imageTourDetails;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    private String toursDetailNameRu;
    private String dayRu;
    private String aboutTourDetailsRu;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String>imageTourDetailsRu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_idRu")
    private Tour tourRu;

    private String toursDetailNameDe;
    private String dayDe;
    private String aboutTourDetailsDe;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String>imageTourDetailsDe;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_idDe")
    private Tour tourDe;

    private String toursDetailNameFr;
    private String dayFr;
    private String aboutTourDetailsFr;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String>imageTourDetailsFr;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_idFr")
    private Tour tourFr;

    private String toursDetailNameEs;
    private String dayEs;
    private String aboutTourDetailsEs;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String>imageTourDetailsEs;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_idEs")
    private Tour tourEs;
}
