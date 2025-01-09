package travel.travel.model.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SightResponse {
    private Long id;
    private String nameOfSight;
    private String description;

}
