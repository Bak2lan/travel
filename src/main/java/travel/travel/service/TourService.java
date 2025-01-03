package travel.travel.service;

import travel.travel.model.dto.request.TourRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TourPaginationResponse;
import travel.travel.model.dto.response.TourResponseGetByID;

public interface TourService {
    SimpleResponse saveTour(TourRequest tourRequest);

    TourResponseGetByID getTourById(Long id);

    TourPaginationResponse getAllTour(int currentPage, int pageSize);

    TourResponseGetByID updateTour(Long id, TourRequest tourRequest);

    SimpleResponse deleteTour(Long id);
}
