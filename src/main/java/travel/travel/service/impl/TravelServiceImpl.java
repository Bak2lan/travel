package travel.travel.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import travel.travel.model.dto.request.TravelRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TravelResponse;
import travel.travel.model.entity.Travel;
import travel.travel.model.mapper.TravelMapper;
import travel.travel.repository.TravelRepository;
import travel.travel.service.TravelService;

import java.time.LocalDateTime;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TravelServiceImpl implements TravelService {

    TravelRepository travelRepository;

    @Override @Transactional
    public SimpleResponse createTravel(TravelRequest travelRequest) {
        Travel travel = TravelMapper.INSTANCE.travelRequestToTravel(travelRequest);
        travelRepository.save(travel);
        return  SimpleResponse.builder()
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .message("success")
                .build();

    }

    @Override
    public TravelResponse getTravelById(Long id) {
        return null;
    }

    @Override
    public SimpleResponse updateTravelById(Long id, TravelRequest travelRequest) {
        return null;
    }

    @Override
    public SimpleResponse deleteTravelById(Long id) {
        return null;
    }
}
