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
@Table(name = "about_kyrgyzstan")
public class AboutKyrgyzstan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "about_kg_seq")
    @SequenceGenerator(name = "about_kg_seq", sequenceName = "about_kg_sequence", initialValue = 6, allocationSize = 1)
    private Long id;
    private String description;
    private String videoFile;
    private String name;

    @ElementCollection
    private List<String> images;

    @OneToOne(cascade = CascadeType.ALL)
    private Sight sight;

    public AboutKyrgyzstan(String description, String videoFile, List<String> images, Sight sight) {
        this.description = description;
        this.videoFile = videoFile;
        this.images = images;
        this.sight = sight;
    }
}