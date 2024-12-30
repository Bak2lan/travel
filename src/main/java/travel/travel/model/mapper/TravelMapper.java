package travel.travel.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import travel.travel.model.dto.request.TravelRequest;
import travel.travel.model.dto.response.TravelResponse;
import travel.travel.model.entity.Travel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TravelMapper {
    TravelMapper INSTANCE = Mappers.getMapper(TravelMapper.class);

    List<Travel> travelRequestsToTravels(List<TravelRequest> travelRequests);

    TravelResponse travelToTravelResponse(Travel travel);

    Travel travelRequestToTravel(TravelRequest travelRequest);

    void updateTravelFromRequest(TravelRequest travelRequest, @MappingTarget Travel travel);

}
