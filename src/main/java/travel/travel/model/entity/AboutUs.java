package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "about_Us")
public class AboutUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String aboutUs;
    String documentation;
    String sustainability;
    String contact;

    @OneToOne(cascade = CascadeType.ALL)
    Travel travel;

    public AboutUs(String aboutUs, String documentation, String sustainability, String contact, Travel travel) {
        this.aboutUs = aboutUs;
        this.documentation = documentation;
        this.sustainability = sustainability;
        this.contact = contact;
        this.travel = travel;
    }
}
