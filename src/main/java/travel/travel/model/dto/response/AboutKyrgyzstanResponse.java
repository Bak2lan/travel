package travel.travel.model.dto.response;

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
public class AboutKyrgyzstanResponse {
    private Long id;
    private String description;
    private String videoFile;
    private String name;
    private List<String> images;
    private AboutType type;
    private Long sighId;

}
