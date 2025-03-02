package travel.travel.service;


import travel.travel.model.dto.request.TravelRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TravelResponse;

public interface TravelService {
    TravelResponse getTravelById(Long id);
    SimpleResponse updateTravel(TravelRequest travelRequest);
}
