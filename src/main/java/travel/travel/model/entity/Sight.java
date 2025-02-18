package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "sights")
public class Sight{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sight_seq")
    @SequenceGenerator(name = "sight_seq", sequenceName = "sight_sequence", initialValue = 13, allocationSize = 1)
    private Long id;
    private String nameOfSight;
    @Column(length = 8000)
    private String description;
    @Column(length = 8000)
    private String titleFromVideo;
    @ElementCollection
    private List<String> images;

    @ManyToOne(fetch = FetchType.LAZY)
    Travel travel;

    @OneToMany(mappedBy = "sight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tour> tours;
    private String coordinatesImage;

    public Sight(String nameOfSight, String description, List<String> images, Travel travel, List<Tour> tours, String coordinatesImage) {
        this.nameOfSight = nameOfSight;
        this.description = description;
        this.images = images;
        this.travel = travel;
        this.tours = tours;
        this.coordinatesImage = coordinatesImage;
    }
}
