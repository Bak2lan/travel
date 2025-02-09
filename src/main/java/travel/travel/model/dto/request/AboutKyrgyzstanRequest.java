package travel.travel.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import travel.travel.model.enums.AboutType;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AboutKyrgyzstanRequest {
    private String description;
    private String videoFile;
    private String name;
    private List<String> images;
    private AboutType type;
}
