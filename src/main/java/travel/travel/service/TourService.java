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

    SimpleResponse saveTourRu(TourRequest tourRequest);
    TourResponseGetByID getTourByIdRu(Long id);
    List<TourGetAllResponse> getAllTourRu();
    List<TourGetAllResponse> getAllTourByPopularRu();
    SimpleResponse  updateTourRu(Long id, TourRequest tourRequest);

    SimpleResponse saveTourDe(TourRequest tourRequest);
    TourResponseGetByID getTourByIdDe(Long id);
    List<TourGetAllResponse> getAllTourDe();
    List<TourGetAllResponse> getAllTourByPopularDe();
    SimpleResponse  updateTourDe(Long id, TourRequest tourRequest);

    SimpleResponse saveTourFr(TourRequest tourRequest);
    TourResponseGetByID getTourByIdFr(Long id);
    List<TourGetAllResponse> getAllTourFr();
    List<TourGetAllResponse> getAllTourByPopularFr();
    SimpleResponse  updateTourFr(Long id, TourRequest tourRequest);

    SimpleResponse saveTourEs(TourRequest tourRequest);
    TourResponseGetByID getTourByIdEs(Long id);
    List<TourGetAllResponse> getAllTourEs();
    List<TourGetAllResponse> getAllTourByPopularEs();
    SimpleResponse  updateTourEs(Long id, TourRequest tourRequest);
}