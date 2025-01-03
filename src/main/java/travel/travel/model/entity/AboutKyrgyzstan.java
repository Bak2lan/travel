package travel.travel.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import travel.travel.model.enums.AboutType;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "about_kyrgyzstan")
public class AboutKyrgyzstan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    String videoFile;
    String name;

    @ElementCollection
    List<String> images;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "sight_id", referencedColumnName = "id",nullable = true)
    Sight sight;

    @Enumerated(EnumType.STRING)
    AboutType type;

    public AboutKyrgyzstan(String description, String videoFile, String name, List<String> images, Sight sight, AboutType type) {
        this.description = description;
        this.videoFile = videoFile;
        this.name = name;
        this.images = images;
        this.sight = sight;
        this.type = type;
    }
}