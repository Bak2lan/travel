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
@Table(name = "about_kyrgyzstan")
public class AboutKyrgyzstan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "about_kyrgyzstan_seq")
    @SequenceGenerator(name = "about_kyrgyzstan_seq", sequenceName = "about_kyrgyzstan_sequence", allocationSize = 1)
    String description;
    String videoFile;
    String name;

    @ElementCollection
    List<String> images;

    @OneToOne(cascade = CascadeType.ALL)
    Sight sight;

    public AboutKyrgyzstan(String description, String videoFile, List<String> images, Sight sight) {
        this.description = description;
        this.videoFile = videoFile;
        this.images = images;
        this.sight = sight;
    }
}