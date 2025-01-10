package travel.travel.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import travel.travel.exception.NotFoundException;
import travel.travel.model.dto.request.SightRequest;
import travel.travel.model.dto.response.SightResponse;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.entity.Sight;
import travel.travel.model.entity.Travel;
import travel.travel.repository.SightRepository;
import travel.travel.repository.TravelRepository;
import travel.travel.service.SightService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SightServiceImpl implements SightService {
    private final SightRepository sightRepository;
    private final TravelRepository travelRepository;

    @Override
    public SimpleResponse createSight(SightRequest sightRequest) {
        if (sightRequest == null) {
            throw new IllegalArgumentException("SightRequest must not be null");
        }
        Travel travel = travelRepository.findById(1L)
                .orElseThrow(() -> new NotFoundException("Travel not found"));

        Sight sight = new Sight();
        sight.setImages(sightRequest.getImages());
        sight.setNameOfSight(sightRequest.getNameOfSight());
        sight.setDescription(sightRequest.getDescription());
        sight.setLatitude(sightRequest.getLatitude());
        sight.setLongitude(sightRequest.getLongitude());
        sight.setTravel(travel);
        sight.getTravel().getSightList().add(sight);
        sightRepository.save(sight);

        return SimpleResponse.builder()
                .message("Sight created successfully")
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public List<SightResponse> findAllSight() {
        List<Sight> sights = sightRepository.findAll();

        if (sights == null || sights.isEmpty()) {
            throw new NotFoundException("No sights found in the database.");
        }

        List<SightResponse> sightResponses = new ArrayList<>();

        for (Sight sight : sights) {
            SightResponse sightResponse = new SightResponse();
            sightResponse.setId(sight.getId() != null ? sight.getId() : 0L);
            sightResponse.setNameOfSight(sight.getNameOfSight() != null ? sight.getNameOfSight() : "No Name");
            sightResponse.setDescription(sight.getDescription() != null ? sight.getDescription() : "No Description");
            sightResponses.add(sightResponse);
        }

        return sightResponses;
    }

    @Override
    public SightResponse findSightById(Long id) {
        try {
            Sight sight = sightRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Sight with id " + id + " not found"));

            if (sight.getNameOfSight() == null || sight.getDescription() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sight data is incomplete");
            }

            return SightResponse.builder()
                    .id(sight.getId())
                    .nameOfSight(sight.getNameOfSight())
                    .description(sight.getDescription())
                    .build();
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }

    @Override
    public SimpleResponse updateSightById(Long id, SightRequest sightRequest) {
        if (sightRequest == null) {
            throw new IllegalArgumentException("SightRequest must not be null");
        }

        Sight sight = sightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sight Not Found"));

        System.out.println("Updating sight: " + sightRequest);

        sight.setNameOfSight(sightRequest.getNameOfSight());
        sight.setDescription(sightRequest.getDescription());
        sight.setImages(sightRequest.getImages());
        sight.setLatitude(sightRequest.getLatitude());
        sight.setLongitude(sightRequest.getLongitude());

        sightRepository.save(sight);

        return SimpleResponse.builder()
                .message("Successfully updated")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public SimpleResponse deleteSightById(Long id) {
        Sight sight = sightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sight Not Found"));

        if (sight.getTravel() != null) {
            ;
            sight.setTravel(null);
        }

        if (sight.getTours() != null && !sight.getTours().isEmpty()) {
            sight.getTours().forEach(tour -> tour.setSight(null));
            sight.setTours(null);
        }

        if (sight.getAbout_kyrgyzstan() != null) {
            sight.getAbout_kyrgyzstan().setSight(null);
            sight.setAbout_kyrgyzstan(null);
        }

        sightRepository.delete(sight);

        return SimpleResponse.builder()
                .message("Successfully deleted")
                .status(HttpStatus.NO_CONTENT)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public List<SightResponse> findAllSight(int currentPage, int pageSize) {
        if (currentPage <= 0) {
            throw new IllegalArgumentException("Page number must be greater than 0");
        }

        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(currentPage - 1, pageSize);

        org.springframework.data.domain.Page<Sight> sightPage = sightRepository.findAll(pageable);
        List<Sight> sights = sightPage.getContent();

        if (sights.isEmpty()) {
            throw new NotFoundException("No sights found");
        }

        List<SightResponse> sightResponses = sights.stream()
                .map(sight -> SightResponse.builder()
                        .id(sight.getId())
                        .nameOfSight(sight.getNameOfSight())
                        .description(sight.getDescription())
                        .build())
                .collect(Collectors.toList());

        return sightResponses;
    }
}