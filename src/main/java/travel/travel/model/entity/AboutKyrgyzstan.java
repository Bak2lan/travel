package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import travel.travel.model.enums.AboutType;
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
    @Column(length = 8000)
    private String description;
    private String videoFile;
    private String name;

    @ElementCollection
    private List<String> images;

    @Enumerated(EnumType.STRING)
    private AboutType type;

    public AboutKyrgyzstan(String description, String videoFile, String name, List<String> images, AboutType type) {
        this.description = description;
        this.videoFile = videoFile;
        this.name = name;
        this.images = images;
        this.type = type;
    }
}