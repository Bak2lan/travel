package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "category_sequence", allocationSize = 1)
    private Long id;
    private int day;
    private String dayTour;

    @ManyToOne
    private Travel travel;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Tour> tour;

    public Category(int day, String dayTour, Travel travel, List<Tour> tour) {
        this.day = day;
        this.dayTour = dayTour;
        this.travel = travel;
        this.tour = tour;
    }
}

