package travel.travel.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
            tour.setPax(tourRequest.pax());
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
        Tour tour = tourRepository.findById(id).orElseThrow(() -> {
            log.error("Tour with id: {} not found", id);
            return new NotFoundException(String.format("Tour with id %s not found", id));
        });
        return tourJDBCTemplate.getTourById(tour.getId());
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
        tour.setPax(tourRequest.pax());
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

    @Override
    public SimpleResponse saveTourRu(TourRequest tourRequest) {
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
            tour.setTourNameRu(tourRequest.tourName());
            tour.setAboutTourRu(tourRequest.aboutTour());
            tour.setDaysByCategoryRu(tourRequest.daysByCategory());
            tour.setNightsRu(tourRequest.nights());
            tour.setPriceRu(tourRequest.price());
            tour.setPaxRu(tourRequest.pax());
            tour.setPaxAndPriceRu(tourRequest.paxAndPrice());
            tour.setDateFromRu(tourRequest.dateFrom());
            tour.setDateToRu(tourRequest.dateTo());
            tour.setImagesRu(tourRequest.images());
            tour.setTravelRu(travel);
            tour.setPopularRu(tourRequest.popular());
            tour.setCoordinatesImageRu(tourRequest.coordinatesImage());
            tour.setWhatIsIncludedRu(tourRequest.whatIsIncluded());
            tour.setWhatIsExcludedRu(tourRequest.whatIsExcluded());

            List<TourDetails> tourDetailsList = new ArrayList<>();
            for (TourDetailsRequest detailsRequest : tourRequest.tourDetailsRequest()) {
                TourDetails tourDetails = new TourDetails();
                tourDetails.setAboutTourDetailsRu(detailsRequest.getAboutTourDetails());
                tourDetails.setToursDetailNameRu(detailsRequest.getToursDetailName());
                tourDetails.setImageTourDetailsRu(detailsRequest.getImageTourDetails());
                tourDetails.setDayRu(detailsRequest.getDay());
                tourDetails.setTourRu(tour);
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
    public TourResponseGetByID getTourByIdRu(Long id) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> {
            log.error("Tour with id: {} not found", id);
            return new NotFoundException(String.format("Tour with id %s not found", id));
        });
        return tourJDBCTemplate.getTourByIdRu(tour.getId());
    }

    @Override
    public List<TourGetAllResponse> getAllTourRu() {
        return tourJDBCTemplate.getAllTourRu();
    }

    @Override
    public List<TourGetAllResponse> getAllTourByPopularRu() {
        return tourJDBCTemplate.getAllTourByPopularRu();
    }

    @Override
    public SimpleResponse updateTourRu(Long id, TourRequest tourRequest) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> {
            log.error("Tour with id {} not found", id);
            return new NotFoundException(String.format("Tour with id %s not found", id));
        });

        tourDetailsRepository.deleteAll(tour.getTourDetails());

        List<TourDetails> updatedTourDetailsList = new ArrayList<>();
        for (TourDetailsRequest detailsRequest : tourRequest.tourDetailsRequest()) {
            TourDetails tourDetails = new TourDetails();
            tourDetails.setAboutTourDetailsRu(detailsRequest.getAboutTourDetails());
            tourDetails.setToursDetailNameRu(detailsRequest.getToursDetailName());
            tourDetails.setImageTourDetailsRu(detailsRequest.getImageTourDetails());
            tourDetails.setDayRu(detailsRequest.getDay());
            tourDetails.setTourRu(tour);
            tourDetailsRepository.save(tourDetails);
            updatedTourDetailsList.add(tourDetails);
        }

        tour.setTourNameRu(tourRequest.tourName());
        tour.setAboutTourRu(tourRequest.aboutTour());
        tour.setDaysByCategoryRu(tourRequest.daysByCategory());
        tour.setNightsRu(tourRequest.nights());
        tour.setPriceRu(tourRequest.price());
        tour.setPaxRu(tourRequest.pax());
        tour.setPaxAndPriceRu(tourRequest.paxAndPrice());
        tour.setDateFromRu(tourRequest.dateFrom());
        tour.setDateToRu(tourRequest.dateTo());
        tour.setImagesRu(tourRequest.images());
        tour.setTourDetails(updatedTourDetailsList);
        tour.setPopularRu(tourRequest.popular());
        tour.setCoordinatesImageRu(tourRequest.coordinatesImage());
        tour.setWhatIsIncludedRu(tourRequest.whatIsIncluded());
        tour.setWhatIsExcludedRu(tourRequest.whatIsExcluded());

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
    public SimpleResponse saveTourDe(TourRequest tourRequest) {
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
            tour.setTourNameDe(tourRequest.tourName());
            tour.setAboutTourDe(tourRequest.aboutTour());
            tour.setDaysByCategoryDe(tourRequest.daysByCategory());
            tour.setNightsDe(tourRequest.nights());
            tour.setPriceDe(tourRequest.price());
            tour.setPaxDe(tourRequest.pax());
            tour.setPaxAndPriceDe(tourRequest.paxAndPrice());
            tour.setDateFromDe(tourRequest.dateFrom());
            tour.setDateToDe(tourRequest.dateTo());
            tour.setImagesDe(tourRequest.images());
            tour.setTravelDe(travel);
            tour.setPopularDe(tourRequest.popular());
            tour.setCoordinatesImageDe(tourRequest.coordinatesImage());
            tour.setWhatIsIncludedDe(tourRequest.whatIsIncluded());
            tour.setWhatIsExcludedDe(tourRequest.whatIsExcluded());

            List<TourDetails> tourDetailsList = new ArrayList<>();
            for (TourDetailsRequest detailsRequest : tourRequest.tourDetailsRequest()) {
                TourDetails tourDetails = new TourDetails();
                tourDetails.setAboutTourDetailsDe(detailsRequest.getAboutTourDetails());
                tourDetails.setToursDetailNameDe(detailsRequest.getToursDetailName());
                tourDetails.setImageTourDetailsDe(detailsRequest.getImageTourDetails());
                tourDetails.setDayDe(detailsRequest.getDay());
                tourDetails.setTourDe(tour);
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
    public TourResponseGetByID getTourByIdDe(Long id) {
        return tourJDBCTemplate.getTourByIdDe(id);
    }

    @Override
    public List<TourGetAllResponse> getAllTourDe() {
        return tourJDBCTemplate.getAllTourDe();
    }

    @Override
    public List<TourGetAllResponse> getAllTourByPopularDe() {
        return tourJDBCTemplate.getAllTourByPopularDe();
    }

    @Override
    public SimpleResponse updateTourDe(Long id, TourRequest tourRequest) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> {
            log.error("Tour with id {} not found", id);
            return new NotFoundException(String.format("Tour with id %s not found", id));
        });

        tourDetailsRepository.deleteAll(tour.getTourDetails());

        List<TourDetails> updatedTourDetailsList = new ArrayList<>();
        for (TourDetailsRequest detailsRequest : tourRequest.tourDetailsRequest()) {
            TourDetails tourDetails = new TourDetails();
            tourDetails.setAboutTourDetailsDe(detailsRequest.getAboutTourDetails());
            tourDetails.setToursDetailNameDe(detailsRequest.getToursDetailName());
            tourDetails.setImageTourDetailsDe(detailsRequest.getImageTourDetails());
            tourDetails.setDayDe(detailsRequest.getDay());
            tourDetails.setTourDe(tour);
            tourDetailsRepository.save(tourDetails);
            updatedTourDetailsList.add(tourDetails);
        }

        tour.setTourNameDe(tourRequest.tourName());
        tour.setAboutTourDe(tourRequest.aboutTour());
        tour.setDaysByCategoryDe(tourRequest.daysByCategory());
        tour.setNightsDe(tourRequest.nights());
        tour.setPriceDe(tourRequest.price());
        tour.setPaxDe(tourRequest.pax());
        tour.setPaxAndPriceDe(tourRequest.paxAndPrice());
        tour.setDateFromDe(tourRequest.dateFrom());
        tour.setDateToDe(tourRequest.dateTo());
        tour.setImagesDe(tourRequest.images());
        tour.setTourDetails(updatedTourDetailsList);
        tour.setPopularDe(tourRequest.popular());
        tour.setCoordinatesImageDe(tourRequest.coordinatesImage());
        tour.setWhatIsIncludedDe(tourRequest.whatIsIncluded());
        tour.setWhatIsExcludedDe(tourRequest.whatIsExcluded());

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
    public SimpleResponse saveTourFr(TourRequest tourRequest) {
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
            tour.setTourNameFr(tourRequest.tourName());
            tour.setAboutTourFr(tourRequest.aboutTour());
            tour.setDaysByCategoryFr(tourRequest.daysByCategory());
            tour.setNightsFr(tourRequest.nights());
            tour.setPriceFr(tourRequest.price());
            tour.setPaxFr(tourRequest.pax());
            tour.setPaxAndPriceFr(tourRequest.paxAndPrice());
            tour.setDateFromFr(tourRequest.dateFrom());
            tour.setDateToFr(tourRequest.dateTo());
            tour.setImagesFr(tourRequest.images());
            tour.setTravelFr(travel);
            tour.setPopularFr(tourRequest.popular());
            tour.setCoordinatesImageFr(tourRequest.coordinatesImage());
            tour.setWhatIsIncludedFr(tourRequest.whatIsIncluded());
            tour.setWhatIsExcludedFr(tourRequest.whatIsExcluded());

            List<TourDetails> tourDetailsList = new ArrayList<>();
            for (TourDetailsRequest detailsRequest : tourRequest.tourDetailsRequest()) {
                TourDetails tourDetails = new TourDetails();
                tourDetails.setAboutTourDetailsFr(detailsRequest.getAboutTourDetails());
                tourDetails.setToursDetailNameFr(detailsRequest.getToursDetailName());
                tourDetails.setImageTourDetailsFr(detailsRequest.getImageTourDetails());
                tourDetails.setDayFr(detailsRequest.getDay());
                tourDetails.setTourFr(tour);
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
    public TourResponseGetByID getTourByIdFr(Long id) {
        return tourJDBCTemplate.getTourByIdFr(id);
    }

    @Override
    public List<TourGetAllResponse> getAllTourFr() {
        return tourJDBCTemplate.getAllTourFr();
    }

    @Override
    public List<TourGetAllResponse> getAllTourByPopularFr() {
        return tourJDBCTemplate.getAllTourByPopularFr();
    }

    @Override
    public SimpleResponse updateTourFr(Long id, TourRequest tourRequest) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> {
            log.error("Tour with id {} not found", id);
            return new NotFoundException(String.format("Tour with id %s not found", id));
        });

        tourDetailsRepository.deleteAll(tour.getTourDetails());

        List<TourDetails> updatedTourDetailsList = new ArrayList<>();
        for (TourDetailsRequest detailsRequest : tourRequest.tourDetailsRequest()) {
            TourDetails tourDetails = new TourDetails();
            tourDetails.setAboutTourDetailsFr(detailsRequest.getAboutTourDetails());
            tourDetails.setToursDetailNameFr(detailsRequest.getToursDetailName());
            tourDetails.setImageTourDetailsFr(detailsRequest.getImageTourDetails());
            tourDetails.setDayFr(detailsRequest.getDay());
            tourDetails.setTourFr(tour);
            tourDetailsRepository.save(tourDetails);
            updatedTourDetailsList.add(tourDetails);
        }

        tour.setTourNameFr(tourRequest.tourName());
        tour.setAboutTourFr(tourRequest.aboutTour());
        tour.setDaysByCategoryFr(tourRequest.daysByCategory());
        tour.setNightsFr(tourRequest.nights());
        tour.setPriceFr(tourRequest.price());
        tour.setPaxFr(tourRequest.pax());
        tour.setPaxAndPriceFr(tourRequest.paxAndPrice());
        tour.setDateFromFr(tourRequest.dateFrom());
        tour.setDateToFr(tourRequest.dateTo());
        tour.setImagesFr(tourRequest.images());
        tour.setTourDetails(updatedTourDetailsList);
        tour.setPopularFr(tourRequest.popular());
        tour.setCoordinatesImageFr(tourRequest.coordinatesImage());
        tour.setWhatIsIncludedFr(tourRequest.whatIsIncluded());
        tour.setWhatIsExcludedFr(tourRequest.whatIsExcluded());

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
    public SimpleResponse saveTourEs(TourRequest tourRequest) {
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
            tour.setTourNameEs(tourRequest.tourName());
            tour.setAboutTourEs(tourRequest.aboutTour());
            tour.setDaysByCategoryEs(tourRequest.daysByCategory());
            tour.setNightsEs(tourRequest.nights());
            tour.setPriceEs(tourRequest.price());
            tour.setPaxEs(tourRequest.pax());
            tour.setPaxAndPriceEs(tourRequest.paxAndPrice());
            tour.setDateFromEs(tourRequest.dateFrom());
            tour.setDateToEs(tourRequest.dateTo());
            tour.setImagesEs(tourRequest.images());
            tour.setTravelEs(travel);
            tour.setPopularEs(tourRequest.popular());
            tour.setCoordinatesImageEs(tourRequest.coordinatesImage());
            tour.setWhatIsIncludedEs(tourRequest.whatIsIncluded());
            tour.setWhatIsExcludedEs(tourRequest.whatIsExcluded());

            List<TourDetails> tourDetailsList = new ArrayList<>();
            for (TourDetailsRequest detailsRequest : tourRequest.tourDetailsRequest()) {
                TourDetails tourDetails = new TourDetails();
                tourDetails.setAboutTourDetailsEs(detailsRequest.getAboutTourDetails());
                tourDetails.setToursDetailNameEs(detailsRequest.getToursDetailName());
                tourDetails.setImageTourDetailsEs(detailsRequest.getImageTourDetails());
                tourDetails.setDayEs(detailsRequest.getDay());
                tourDetails.setTourEs(tour);
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
    public TourResponseGetByID getTourByIdEs(Long id) {
        return tourJDBCTemplate.getTourByIdEs(id);
    }

    @Override
    public List<TourGetAllResponse> getAllTourEs() {
        return tourJDBCTemplate.getAllTourEs();
    }

    @Override
    public List<TourGetAllResponse> getAllTourByPopularEs() {
        return tourJDBCTemplate.getAllTourByPopularEs();
    }

    @Override
    public SimpleResponse updateTourEs(Long id, TourRequest tourRequest) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> {
            log.error("Tour with id {} not found", id);
            return new NotFoundException(String.format("Tour with id %s not found", id));
        });

        tourDetailsRepository.deleteAll(tour.getTourDetails());

        List<TourDetails> updatedTourDetailsList = new ArrayList<>();
        for (TourDetailsRequest detailsRequest : tourRequest.tourDetailsRequest()) {
            TourDetails tourDetails = new TourDetails();
            tourDetails.setAboutTourDetailsEs(detailsRequest.getAboutTourDetails());
            tourDetails.setToursDetailNameEs(detailsRequest.getToursDetailName());
            tourDetails.setImageTourDetailsEs(detailsRequest.getImageTourDetails());
            tourDetails.setDayEs(detailsRequest.getDay());
            tourDetails.setTourEs(tour);
            tourDetailsRepository.save(tourDetails);
            updatedTourDetailsList.add(tourDetails);
        }

        tour.setTourNameEs(tourRequest.tourName());
        tour.setAboutTourEs(tourRequest.aboutTour());
        tour.setDaysByCategoryEs(tourRequest.daysByCategory());
        tour.setNightsEs(tourRequest.nights());
        tour.setPriceEs(tourRequest.price());
        tour.setPaxEs(tourRequest.pax());
        tour.setPaxAndPriceEs(tourRequest.paxAndPrice());
        tour.setDateFromEs(tourRequest.dateFrom());
        tour.setDateToEs(tourRequest.dateTo());
        tour.setImagesEs(tourRequest.images());
        tour.setTourDetails(updatedTourDetailsList);
        tour.setPopularEs(tourRequest.popular());
        tour.setCoordinatesImageEs(tourRequest.coordinatesImage());
        tour.setWhatIsIncludedEs(tourRequest.whatIsIncluded());
        tour.setWhatIsExcludedEs(tourRequest.whatIsExcluded());

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

}