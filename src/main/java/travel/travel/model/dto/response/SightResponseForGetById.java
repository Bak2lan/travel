package travel.travel.model.dto.response;

import lombok.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SightResponseForGetById {
    private Long id;
    private List<String>images;
    private String nameOfSight;
    private String description;
    private String titleFromVideo;
    private String coordinatesImage;
}
