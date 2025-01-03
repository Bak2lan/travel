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
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TourPaginationResponse;
import travel.travel.model.dto.response.TourResponseForPagination;
import travel.travel.model.dto.response.TourResponseGetByID;
import travel.travel.model.entity.Category;
import travel.travel.model.entity.Sight;
import travel.travel.model.entity.Tour;
import travel.travel.model.entity.Travel;
import travel.travel.repository.CategoryRepository;
import travel.travel.repository.SightRepository;
import travel.travel.repository.TourRepository;
import travel.travel.repository.TravelRepository;
import travel.travel.service.TourService;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class TourServiceImpl implements TourService {

    private final TravelRepository travelRepository;
    private final SightRepository sightRepository;
    private final CategoryRepository categoryRepository;
    private final TourRepository tourRepository;

    public TourServiceImpl(TravelRepository travelRepository, SightRepository sightRepository, CategoryRepository categoryRepository, TourRepository tourRepository) {
        this.travelRepository = travelRepository;
        this.sightRepository = sightRepository;
        this.categoryRepository = categoryRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    public SimpleResponse saveTour(TourRequest tourRequest) {
        if (tourRequest.tourName().isBlank()) {
            log.warn("Attempted to save a tour with empty name");
            throw new BadRequestExeption("Tour name can not be empty");
        }
        if (tourRequest.pax() < 0 || tourRequest.days() < 0 || tourRequest.nights() < 0) {
            log.warn("Details tour contain invalid values (pax people, days, nights) : pax people ={}, days = {}, nights = {}",
                    tourRequest.pax(), tourRequest.days(), tourRequest.nights());
            throw new BadRequestExeption("Details of tour including pax people, days, nights should be greater than 0");
        }
        Travel travel = travelRepository.findById(1L).orElseThrow(() -> {
            log.error("Travel not found with id {}", 1L);
            return new NotFoundException(String.format("Travel with id %s not found", 1));
        });
        Sight sight = sightRepository.findById(tourRequest.sightId()).orElseThrow(() -> {
            log.error("Sight with id {} not found ", tourRequest.sightId());
            return new NotFoundException(String.format("Sight with id %s not found", tourRequest.sightId()));
        });
        Category category = categoryRepository.findById(tourRequest.categoryId()).orElseThrow(() -> {
            log.error("Category with id {} not found", tourRequest.categoryId());
            return new NotFoundException(String.format("Category with id %s not found", tourRequest.categoryId()));
        });
        Tour tour = new Tour(
                tourRequest.latitude(),
                tourRequest.longitude(),
                tourRequest.tourName(),
                tourRequest.aboutTour(),
                tourRequest.days(),
                tourRequest.nights(),
                tourRequest.price(),
                tourRequest.pax(),
                tourRequest.dateFrom(),
                tourRequest.dateTo(),
                category,
                tourRequest.images(),
                tourRequest.tourDetails(),
                travel,
                sight
        );
        tourRepository.save(tour);
        log.info("Tour successfully saved");
        return SimpleResponse
                .builder()
                .message("CREATED")
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public TourResponseGetByID getTourById(Long id) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> {
            log.error("Tour with id {} not found", id);
            return new NotFoundException(String.format("Tour with id %s not found", id));
        });
        log.info("Fetching tour with id {}", id);
        return TourResponseGetByID
                .builder()
                .id(tour.getId())
                .latitude(tour.getLatitude())
                .longitude(tour.getLongitude())
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
    public TourResponseGetByID updateTour(Long id, TourRequest tourRequest) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> {
            log.error("Tour with id {}", id);
            return new NotFoundException(String.format("Tour with id %s not foun", id));
        });
        Category category = categoryRepository.findById(tourRequest.categoryId()).orElseThrow(() -> {
            log.error("Category with id: {} not found ", tourRequest.categoryId());
            return new NotFoundException(String.format("Category with id %s not found", tourRequest.categoryId()));
        });
        Sight sight = sightRepository.findById(tourRequest.sightId()).orElseThrow(() -> {
            log.error("Sight with id: {} not found ", tourRequest.sightId());
            return new NotFoundException(String.format("Sight with id %s not found", tourRequest.sightId()));
        });
        tour.setLatitude(tourRequest.latitude());
        tour.setLongitude(tourRequest.longitude());
        tour.setTourName(tourRequest.tourName());
        tour.setAboutTour(tourRequest.aboutTour());
        tour.setDays(tourRequest.days());
        tour.setNights(tourRequest.nights());
        tour.setPrice(tourRequest.price());
        tour.setPax(tourRequest.pax());
        tour.setDateFrom(tourRequest.dateFrom());
        tour.setDateTo(tourRequest.dateTo());
        tour.setCategory(category);
        tour.setImages(tourRequest.images());
        tour.setDetailsOfTour(tourRequest.tourDetails());
        tour.setSight(sight);
        tourRepository.save(tour);
        log.info("Tour with id {} is successfully updated", id);
        return TourResponseGetByID
                .builder()
                .id(tour.getId())
                .latitude(tour.getLatitude())
                .longitude(tour.getLongitude())
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
}
