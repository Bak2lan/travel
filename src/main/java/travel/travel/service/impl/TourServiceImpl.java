package travel.travel.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travel.travel.exception.BadRequestExeption;
import travel.travel.exception.NotFoundException;
import travel.travel.model.dto.request.TourDetailsRequest;
import travel.travel.model.dto.request.TourRequest;
import travel.travel.model.dto.response.*;
import travel.travel.model.entity.Tour;
import travel.travel.model.entity.TourDetails;
import travel.travel.model.entity.Travel;
import travel.travel.repository.*;
import travel.travel.repository.JDBCTemplate.TourJDBCTemplate;
import travel.travel.service.TourService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TravelRepository travelRepository;
    private final TourRepository tourRepository;
    private final TourJDBCTemplate tourJDBCTemplate;
    private final TourDetailsRepository tourDetailsRepository;

    @Override
    public SimpleResponse saveTour(TourRequest tourRequest) {
        try {
            log.info("Attempting to save tour: {}", tourRequest.tourName());

            if (tourRequest.tourName().isBlank()) {
                log.warn("Attempted to save a tour with empty name");
                throw new BadRequestExeption("Tour name cannot be empty");
            }
            if (tourRequest.daysByCategory() < 0 || tourRequest.nights() < 0) {
                log.warn("Details of the tour contain invalid values: daysByCategoryId = {}, nights = {}",
                        tourRequest.daysByCategory(), tourRequest.nights());
                throw new BadRequestExeption("Details of the tour including daysByCategoryId and nights should be greater than 0");
            }

            Travel travel = travelRepository.findById(1L).orElseThrow(() -> {
                log.error("Travel not found with id {}", 1L);
                return new NotFoundException(String.format("Travel with id %s not found", 1));
            });

            Tour tour = new Tour();
            tour.setTourName(tourRequest.tourName());
            tour.setAboutTour(tourRequest.aboutTour());
            tour.setDaysByCategory(tourRequest.daysByCategory());
            tour.setNights(tourRequest.nights());
            tour.setPrice(tourRequest.price());
            tour.setPaxAndPrice(tourRequest.paxAndPrice());
            tour.setDateFrom(tourRequest.dateFrom());
            tour.setDateTo(tourRequest.dateTo());
            tour.setImages(tourRequest.images());
            tour.setTravel(travel);
            tour.setPopular(tourRequest.popular());
            tour.setCoordinatesImage(tourRequest.coordinatesImage());
            tour.setWhatIsIncluded(tourRequest.whatIsIncluded());
            tour.setWhatIsExcluded(tourRequest.whatIsExcluded());

            List<TourDetails> tourDetailsList = new ArrayList<>();
            for (TourDetailsRequest detailsRequest : tourRequest.tourDetailsRequest()) {
                TourDetails tourDetails = new TourDetails();
                tourDetails.setAboutTourDetails(detailsRequest.getAboutTourDetails());
                tourDetails.setToursDetailName(detailsRequest.getToursDetailName());
                tourDetails.setImageTourDetails(detailsRequest.getImageTourDetails());
                tourDetails.setDay(detailsRequest.getDay());
                tourDetails.setTour(tour);
                tourDetailsRepository.save(tourDetails);
                tourDetailsList.add(tourDetails);
            }

            tour.setTourDetails(tourDetailsList);
            tourRepository.save(tour);

            log.info("Tour successfully saved");

            return SimpleResponse.builder()
                    .message("Created tour with id: " + tour.getId())
                    .status(HttpStatus.CREATED)
                    .timestamp(LocalDateTime.now())
                    .build();
        } catch (Exception ex) {
            log.error("Error saving tour: {}", ex.getMessage(), ex);
            throw new RuntimeException("Internal server error: " + ex.getMessage());
        }
    }

    @Override
    public TourResponseGetByID getTourById(Long id) {
        return tourJDBCTemplate.getTourById(id);
    }

    @Override
    public List<TourGetAllResponse> getAllTour() {
        return tourJDBCTemplate.getAllTour();
    }

    @Override
    public List<TourGetAllResponse> getAllTourByPopular(){
        return tourJDBCTemplate.getAllTourByPopular();
    }

    @Override
    public SimpleResponse updateTour(Long id, TourRequest tourRequest) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> {
            log.error("Tour with id {} not found", id);
            return new NotFoundException(String.format("Tour with id %s not found", id));
        });

        tourDetailsRepository.deleteAll(tour.getTourDetails());

        List<TourDetails> updatedTourDetailsList = new ArrayList<>();
        for (TourDetailsRequest detailsRequest : tourRequest.tourDetailsRequest()) {
            TourDetails tourDetails = new TourDetails();
            tourDetails.setAboutTourDetails(detailsRequest.getAboutTourDetails());
            tourDetails.setToursDetailName(detailsRequest.getToursDetailName());
            tourDetails.setImageTourDetails(detailsRequest.getImageTourDetails());
            tourDetails.setDay(detailsRequest.getDay());
            tourDetails.setTour(tour);
            tourDetailsRepository.save(tourDetails);
            updatedTourDetailsList.add(tourDetails);
        }

        tour.setTourName(tourRequest.tourName());
        tour.setAboutTour(tourRequest.aboutTour());
        tour.setDaysByCategory(tourRequest.daysByCategory());
        tour.setNights(tourRequest.nights());
        tour.setPrice(tourRequest.price());
        tour.setPaxAndPrice(tourRequest.paxAndPrice());
        tour.setDateFrom(tourRequest.dateFrom());
        tour.setDateTo(tourRequest.dateTo());
        tour.setImages(tourRequest.images());
        tour.setTourDetails(updatedTourDetailsList);
        tour.setPopular(tourRequest.popular());
        tour.setCoordinatesImage(tourRequest.coordinatesImage());
        tour.setWhatIsIncluded(tourRequest.whatIsIncluded());
        tour.setWhatIsExcluded(tourRequest.whatIsExcluded());

        tour.setTourDetails(updatedTourDetailsList);
        tourRepository.save(tour);
        log.info("Tour with id {} is successfully updated", id);
        return SimpleResponse
                .builder()
                .message("Successfully updated tour with id: " + tour.getId())
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public SimpleResponse deleteTour(Long id) {


        Tour tour = tourRepository.findById(id).orElseThrow(() -> {
            log.error("Tour with id: {} not found", id);
            return new NotFoundException(String.format("Tour with id %s not found", id));
        });

        tourDetailsRepository.deleteAll(tour.getTourDetails());

        Travel travel = tour.getTravel();
        if (travel != null) {
            travel.getTourList().remove(tour);
        }

        tour.setTourName(null);
        tour.setTourDetails(null);

        tourRepository.delete(tour);
        log.info("Tour with id {} is successfully deleted", id);

        return SimpleResponse
                .builder()
                .message("Successfully deleted")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
    }
}