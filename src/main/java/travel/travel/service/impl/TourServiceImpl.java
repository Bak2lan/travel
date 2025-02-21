package travel.travel.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travel.travel.exception.BadRequestExeption;
import travel.travel.exception.NotFoundException;
import travel.travel.model.dto.request.TourRequest;
import travel.travel.model.dto.response.*;
import travel.travel.model.entity.Category;
import travel.travel.model.entity.Tour;
import travel.travel.model.entity.Travel;
import travel.travel.repository.CategoryRepository;
import travel.travel.repository.JDBCTemplate.TourJDBCTemplate;
import travel.travel.repository.SightRepository;
import travel.travel.repository.TourRepository;
import travel.travel.repository.TravelRepository;
import travel.travel.service.TourService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TourServiceImpl implements TourService {

    private final TravelRepository travelRepository;
    private final TourRepository tourRepository;
    private final TourJDBCTemplate tourJDBCTemplate;

    public TourServiceImpl(TravelRepository travelRepository, SightRepository sightRepository, CategoryRepository categoryRepository, TourRepository tourRepository, TourJDBCTemplate tourJDBCTemplate) {
        this.travelRepository = travelRepository;
        this.tourRepository = tourRepository;
        this.tourJDBCTemplate = tourJDBCTemplate;
    }

    @Override
    public SimpleResponse saveTour(TourRequest tourRequest) {
        try {
            log.info("Attempting to save tour: {}", tourRequest.tourName());

            if (tourRequest.tourName().isBlank()) {
                log.warn("Attempted to save a tour with empty name");
                throw new BadRequestExeption("Tour name can not be empty");
            }
            if (tourRequest.days() < 0 || tourRequest.nights() < 0) {
                log.warn("Details tour contain invalid values (max people, days, nights) : max people ={}, days = {}, nights = {}"
                       , tourRequest.days(), tourRequest.nights());
                throw new BadRequestExeption("Details of tour including max people, days, nights should be greater than 0");
            }

            Travel travel = travelRepository.findById(1L).orElseThrow(() -> {
                log.error("Travel not found with id {}", 1L);
                return new NotFoundException(String.format("Travel with id %s not found", 1));
            });


            Tour tour = new Tour();
            tour.setTourName(tourRequest.tourName());
            tour.setAboutTour(tourRequest.aboutTour());
            tour.setDays(tourRequest.days());
            tour.setNights(tourRequest.nights());
            tour.setPrice(tourRequest.price());
            tour.setPax(tourRequest.pax());
            tour.setDateFrom(tourRequest.dateFrom());
            tour.setDateTo(tourRequest.dateTo());
            tour.setImages(tourRequest.images());
            tour.setDetailsOfTour(tourRequest.tourDetails());
            tour.setTravel(travel);
            tour.setValueCategory(tourRequest.valueCategory());
            tour.setPopular(tourRequest.popular());
            tour.setCoordinatesImage(tourRequest.coordinatesImage());
            tourRepository.save(tour);
            log.info("Tour successfully saved");

            return SimpleResponse.builder()
                    .message("CREATED")
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
    public TourPaginationResponse getAllTour(int currentPage, int pageSize) {
        log.info("Fetching all tours");
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<TourResponseForPagination> allTour = tourRepository.getAllTour(pageable);
        return TourPaginationResponse
                .builder()
                .tourResponses(allTour.getContent())
                .currentPage(allTour.getNumber() + 1)
                .pageSize(allTour.getTotalPages())
                .build();
    }

    @Override
    public TourPaginationResponse getAllTourByPopular(int currentPage, int pageSize) {
        log.info("Fetching all tours by popular");
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<TourResponseForPagination> allTour = tourRepository.getAllTourByPopular(pageable);
        return TourPaginationResponse
                .builder()
                .tourResponses(allTour.getContent())
                .currentPage(allTour.getNumber() + 1)
                .pageSize(allTour.getTotalPages())
                .build();
    }

    @Override
    public TourResponseGetByID updateTour(Long id, TourRequest tourRequest) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> {
            log.error("Tour with id {} not found", id);
            return new NotFoundException(String.format("Tour with id %s not found", id));
        });

        tour.setTourName(tourRequest.tourName());
        tour.setAboutTour(tourRequest.aboutTour());
        tour.setDays(tourRequest.days());
        tour.setNights(tourRequest.nights());
        tour.setPrice(tourRequest.price());
        tour.setPax(tourRequest.pax());
        tour.setDateFrom(tourRequest.dateFrom());
        tour.setDateTo(tourRequest.dateTo());
        tour.setImages(tourRequest.images());
        tour.setDetailsOfTour(tourRequest.tourDetails());
        tour.setPopular(tourRequest.popular());
        tour.setCoordinatesImage(tourRequest.coordinatesImage());
        tourRepository.save(tour);
        log.info("Tour with id {} is successfully updated", id);
        return TourResponseGetByID.builder()
                .id(tour.getId())
                .tourName(tour.getTourName())
                .aboutTour(tour.getAboutTour())
                .days(tour.getDays())
                .nights(tour.getNights())
                .price(tour.getPrice())
                .pax(tour.getPax())
                .dateFrom(tour.getDateFrom())
                .dateTo(tour.getDateTo())
                .detailsOfTour(tour.getDetailsOfTour())
                .build();
    }

    @Override
    public SimpleResponse deleteTour(Long id) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> {
            log.error("Tour with id: {} not found", id);
            return new NotFoundException(String.format("Tour with id %s not found", id));
        });

        Category category = tour.getCategory();
        category.getTour().remove(tour);
        tour.setCategory(null);

        Travel travel = tour.getTravel();
        travel.getTourList().remove(tour);
        tour.setTourName(null);
        tourRepository.delete(tour);
        log.info("Tour with id {} is successfully deleted", id);

        return SimpleResponse
                .builder()
                .message("Successfully deleted")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public List<TourGetAllResponse> getAllTours() {
        List<Tour> all = tourRepository.findAll();
        return all.stream()
                .map(tour -> TourGetAllResponse
                        .builder()
                        .id(tour.getId())
                        .tourName(tour.getTourName())
                        .image(tour.getImages()!=null && !tour.getImages().isEmpty() ? tour.getImages().get(0): null)
                        .aboutTour(tour.getAboutTour())
                        .days(tour.getDays())
                        .nights(tour.getNights())
                        .price(tour.getPrice())
                        .pax(tour.getPax())
                        .dateFrom(tour.getDateFrom())
                        .dateTo(tour.getDateTo()).build()).toList();
    }

    @Override
    public Map<Integer, List<TourGetAllResponse>> getAllToursSortByCategory() {
        List<TourGetAllResponse> allTours = getAllTours();
      return   allTours.stream()
                .collect(Collectors.groupingBy(TourGetAllResponse::days));
    }
}