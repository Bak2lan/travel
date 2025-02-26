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
    private String aboutTourDetails;
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String>imageTourDetails;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;
}