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
}
