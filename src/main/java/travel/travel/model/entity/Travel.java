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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aboutUs;
    private String documentation;
    private String sustainability;
    private String contact;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Tour> tourList;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Sight> sightList;

    public Travel(String aboutUs, String documentation, String sustainability, String contact, List<User> users, List<Tour> tourList, List<Sight> sightList) {
        this.aboutUs = aboutUs;
        this.documentation = documentation;
        this.sustainability = sustainability;
        this.contact = contact;
        this.users = users;
        this.tourList = tourList;
        this.sightList = sightList;
    }
}