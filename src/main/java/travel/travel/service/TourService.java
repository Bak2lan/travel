package travel.travel.service;

import travel.travel.model.dto.request.TourRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TourGetAllResponse;
import travel.travel.model.dto.response.TourPaginationResponse;
import travel.travel.model.dto.response.TourResponseGetByID;

import java.util.List;
import java.util.Map;

public interface TourService {
    SimpleResponse saveTour(TourRequest tourRequest);
    TourResponseGetByID getTourById(Long id);
    TourPaginationResponse getAllTour(int currentPage, int pageSize);
    TourPaginationResponse getAllTourByPopular(int currentPage, int pageSize);
    TourResponseGetByID updateTour(Long id, TourRequest tourRequest);
    SimpleResponse deleteTour(Long id);
}