package travel.travel.service;


import travel.travel.model.dto.request.TravelRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TravelResponse;

public interface TravelService {

    SimpleResponse createTravel(TravelRequest travelRequest);

    TravelResponse getTravelById(Long id);

    SimpleResponse updateTravelById(Long id, TravelRequest travelRequest);

    SimpleResponse deleteTravelById(Long id);


}
