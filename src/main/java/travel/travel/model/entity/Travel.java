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
    @Column(length = 10000)
    private String aboutUsName;
    @Column(length = 10000)
    private String aboutUs;
    @Column(length = 10000)
    private String documentationName;
    @Column(length = 10000)
    private String documentation;
    @Column(length = 10000)
    private String sustainabilityName;
    @Column(length = 10000)
    private String sustainability;
    @Column(length = 10000)
    private String certificationName;
    @Column(length = 10000)
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