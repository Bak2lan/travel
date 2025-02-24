package travel.travel.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class TourDetailsRequest {
    private String toursDetailName;
    private String days;
    private String distance;
    private String aboutTourDetails;
    private List<String> imageTourDetails;

    public TourDetailsRequest() {
    }

    public TourDetailsRequest(String toursDetailName, String days, String distance, String aboutTourDetails, List<String> imageTourDetails) {
        this.toursDetailName = toursDetailName;
        this.days = days;
        this.distance = distance;
        this.aboutTourDetails = aboutTourDetails;
        this.imageTourDetails = imageTourDetails;
    }

}