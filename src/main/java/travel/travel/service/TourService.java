package travel.travel.service;

import travel.travel.model.dto.request.TourRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TourResponse;

public interface TourService {
    SimpleResponse saveTour(TourRequest tourRequest);

    TourResponse getTourById(Long id);


}
