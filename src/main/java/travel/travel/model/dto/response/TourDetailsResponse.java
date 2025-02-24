package travel.travel.model.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class TourDetailsResponse {
    private Long id;
    private String toursDetailName;
    private String days;
    private String distance;
    private String aboutTourDetails;
    private List<String> imageTourDetails;

    public TourDetailsResponse() {
    }

    public TourDetailsResponse(Long id, String toursDetailName, String days, String distance, String aboutTourDetails, List<String> imageTourDetails) {
        this.id = id;
        this.toursDetailName = toursDetailName;
        this.days = days;
        this.distance = distance;
        this.aboutTourDetails = aboutTourDetails;
        this.imageTourDetails = imageTourDetails;
    }
}
