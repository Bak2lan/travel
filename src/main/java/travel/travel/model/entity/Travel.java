package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "travels")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "travel_seq")
    @SequenceGenerator(name = "travel_seq", sequenceName = "travel_sequence", initialValue = 2, allocationSize = 1)
    private Long id;
    private String aboutUs;
    private String documentation;
    private String sustainability;
    private String contact;

    @OneToOne(mappedBy = "travel", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<Tour> tourList;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<Sight> sightList;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL)
    private List<Category> category;

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

}
