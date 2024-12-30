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
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int day;
    String dayTour;

    @ManyToOne
    Travel travel;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    List<Tour> tour; //

    public Category(int day, String dayTour, Travel travel, List<Tour> tour) {
        this.day = day;
        this.dayTour = dayTour;
        this.travel = travel;
        this.tour = tour;
    }
}

