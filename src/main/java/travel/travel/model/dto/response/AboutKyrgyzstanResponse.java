package travel.travel.model.dto.response;

import lombok.*;
import travel.travel.model.enums.AboutType;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AboutKyrgyzstanResponse {
    private Long id;
    private List<String> description;
    private String videoFile;
    private String name;
    private List<String> images;
    private AboutType type;

}
