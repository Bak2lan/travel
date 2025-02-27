package travel.travel.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class TourDetailsRequest {
    private String toursDetailName;
    private String day;
    private String aboutTourDetails;
    private List<String> imageTourDetails;
}