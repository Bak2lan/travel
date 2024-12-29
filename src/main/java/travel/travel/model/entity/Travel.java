package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "travels")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(mappedBy = "travel",cascade = CascadeType.ALL)
    User user;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    List<Tour> tourList;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    List<Sight> sightList;

    @OneToOne(mappedBy = "travel", fetch = FetchType.LAZY)
    AboutUs aboutUs;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    List<Category> category;
}
