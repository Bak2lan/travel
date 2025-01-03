package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @OneToOne(mappedBy = "travel",cascade = CascadeType.ALL)
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
