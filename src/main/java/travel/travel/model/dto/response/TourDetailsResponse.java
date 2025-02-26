package travel.travel.model.dto.response;

import lombok.Getter;
import java.util.List;

@Getter
public class TourDetailsResponse {
    private Long id;
    private String toursDetailName;
    private String day;
    private String aboutTourDetails;
    private List<String> imageTourDetails;

    public TourDetailsResponse() {
    }

    public TourDetailsResponse(Long id, String toursDetailName, String day, String aboutTourDetails, List<String> imageTourDetails) {
        this.id = id;
        this.toursDetailName = toursDetailName;
        this.day = day;
        this.aboutTourDetails = aboutTourDetails;
        this.imageTourDetails = imageTourDetails;
    }
}