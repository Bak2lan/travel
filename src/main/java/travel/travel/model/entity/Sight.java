package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.*;
import travel.travel.model.Location;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "sights")
public class Sight extends Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sight_seq")
    @SequenceGenerator(name = "sight_seq", sequenceName = "sight_sequence", initialValue = 6, allocationSize = 1)
    private Long id;
    private String nameOfSight;
    private String description;

    @ElementCollection
    private List<String> images;

    @ManyToOne
    Travel travel;

    @OneToMany(mappedBy = "sight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tour> tours;

    @OneToOne(mappedBy = "sight")
    private AboutKyrgyzstan about_kyrgyzstan;

    public Sight(double latitude, double longitude, String nameOfSight, String description, List<String> images, Travel travel, List<Tour> tours, AboutKyrgyzstan about_kyrgyzstan) {
        super(latitude, longitude);
        this.nameOfSight = nameOfSight;
        this.description = description;
        this.images = images;
        this.travel = travel;
        this.tours = tours;
        this.about_kyrgyzstan = about_kyrgyzstan;
    }
}
