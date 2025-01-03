package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "travel_seq")
    @SequenceGenerator(name = "travel_seq", sequenceName = "travel_sequence", allocationSize = 1)
    Long id;
    String aboutUs;
    String documentation;
    String sustainability;
    String contact;

    @OneToOne(mappedBy = "travel",cascade = CascadeType.ALL)
    User user;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    List<Tour> tourList;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    List<Sight> sightList;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    List<Category> category;

    public Travel(String aboutUs, String documentation, String sustainability, String contact, User user, List<Tour> tourList, List<Sight> sightList, List<Category> category) {
        this.aboutUs = aboutUs;
        this.documentation = documentation;
        this.sustainability = sustainability;
        this.contact = contact;
        this.user = user;
        this.tourList = tourList;
        this.sightList = sightList;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", aboutUs='" + aboutUs + '\'' +
                ", documentation='" + documentation + '\'' +
                ", sustainability='" + sustainability + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
