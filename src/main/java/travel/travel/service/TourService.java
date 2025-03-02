package travel.travel.service;

import travel.travel.model.dto.request.TourRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TourGetAllResponse;
import travel.travel.model.dto.response.TourResponseGetByID;
import java.util.List;

public interface TourService {
    SimpleResponse saveTour(TourRequest tourRequest);
    TourResponseGetByID getTourById(Long id);
    List<TourGetAllResponse> getAllTour();
    List<TourGetAllResponse> getAllTourByPopular();
    SimpleResponse  updateTour(Long id, TourRequest tourRequest);
    SimpleResponse deleteTour(Long id);
}