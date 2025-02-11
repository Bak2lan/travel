package travel.travel.model.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

@Builder
public class SightResponse {
    private Long id;
    private String image;
    private String nameOfSight;
    private String description;
    private String titleFromVideo;

    public SightResponse(Long id, String image, String nameOfSight, String description, String titleFromVideo) {
        this.id = id;
        this.image = image;
        this.nameOfSight = nameOfSight;
        this.description = description;
        this.titleFromVideo = titleFromVideo;
    }

    public SightResponse(Long id, String image, String nameOfSight, String description) {
        this.id = id;
        this.image = image;
        this.nameOfSight = nameOfSight;
        this.description = description;
    }
}
