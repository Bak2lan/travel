package travel.travel.model.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SightResponse {
    private Long id;
    private String image;
    private String nameOfSight;
    private String description;
    private String titleFromVideo;
}
