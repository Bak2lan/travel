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

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TravelServiceImpl implements TravelService {

    TravelRepository travelRepository;
    TravelMapper travelMapper;

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
       Travel travel =  travelRepository.findById(id).
                orElseThrow(()-> new NotFoundException("Travel with id "+id+" not found"));
        return travelMapper.travelToTravelResponse(travel);

    }

    @Override
    public SimpleResponse updateTravelById(Long id, TravelRequest travelRequest) {
       Travel travel =  travelRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Travel with id "+id+" not found"));

       travel.setAboutUs(travelRequest.aboutUs());
       travel.setContact(travel.getContact());
       travel.setDocumentation(travelRequest.documentation());
       travel.setSustainability(travelRequest.sustainability());
       travelRepository.save(travel);
       return SimpleResponse.builder()
               .message("success")
               .status(HttpStatus.OK)
               .timestamp(LocalDateTime.now())
               .build();

    }

    @Override
    public SimpleResponse deleteTravelById(Long id) {
      Travel travel =   travelRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Travel with id"+ id+" not found"));
travelRepository.delete(travel);
        return SimpleResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("success")
                .status(HttpStatus.OK)
                .build();
    }
}
