package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "travels")
public class Travel {
    @Id
    private Long id;
    private String aboutUsName;
    private String aboutUs;
    private String documentationName;
    private String documentation;
    private String sustainabilityName;
    private String sustainability;
    private String certificationName;
    private String certification;
    private String address;
    private String phoneNumber;
    private String email;
    private String image;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Tour> tourList;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Sight> sightList;
}