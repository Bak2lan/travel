package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import travel.travel.model.Location;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "sights")
public class Sight extends Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nameOfSight;
    String description;

    @ElementCollection
    List<String> images;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    Travel travel;

    @OneToMany(mappedBy = "sight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tour> tours;

    @OneToOne(mappedBy = "sight")
    AboutKyrgyzstan about_kyrgyzstan;


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
