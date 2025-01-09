package travel.travel.model.dto.request;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SightRequest {
    private String nameOfSight;
    private String description;
    @ElementCollection
    private List<String> images;
}
