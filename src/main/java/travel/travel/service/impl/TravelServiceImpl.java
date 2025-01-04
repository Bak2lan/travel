package travel.travel.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import travel.travel.exception.NotFoundException;
import travel.travel.model.dto.request.TravelRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TravelResponse;
import travel.travel.model.entity.Travel;
import travel.travel.model.mapper.TravelMapper;
import travel.travel.repository.TravelRepository;
import travel.travel.service.TravelService;

import java.time.LocalDateTime;

@Service()
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TravelServiceImpl implements TravelService {

    TravelRepository travelRepository;
    TravelMapper travelMapper;

    @Override
    @Transactional
    public SimpleResponse createTravel(TravelRequest travelRequest) {
        log.info("Creating travel: {}", travelRequest);
        Travel travel = travelMapper.travelRequestToTravel(travelRequest);
        travelRepository.save(travel);
        log.info("Travel successfully created: {}", travel);
        return SimpleResponse.builder()
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .message("success")
                .build();

    }

    @Override
    public TravelResponse getTravelById(Long id) {
        log.info("Fetching travel by id: {}", id);
        Travel travel = travelRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Travel with id " + id + " not found"));
        log.info("Found travel: {}", travel);
        return travelMapper.travelToTravelResponse(travel);

    }

    @Override
    public SimpleResponse updateTravelById(Long id, TravelRequest travelRequest) {
        log.info("Updating travel with id: {}, request: {}", id, travelRequest);
        Travel travel = travelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Travel with id " + id + " not found"));
        travelMapper.updateTravelFromRequest(travelRequest, travel);
        travelRepository.save(travel);
        log.info("Travel successfully updated: {}", travel);
        return SimpleResponse.builder()
                .message("Travel successfully updated")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

    }

    @Override
    public SimpleResponse deleteTravelById(Long id) {
        log.info("Deleting travel with id: {}", id);
        Travel travel = travelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Travel with id" + id + " not found"));
        travelRepository.delete(travel);
        return SimpleResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("Travel with id " + id + " successfully deleted")
                .status(HttpStatus.OK)
                .build();
    }
}
