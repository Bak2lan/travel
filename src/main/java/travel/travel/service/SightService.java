package travel.travel.service;

import travel.travel.model.dto.request.SightRequest;
import travel.travel.model.dto.response.SightResponse;
import travel.travel.model.dto.response.SimpleResponse;

import java.awt.print.Pageable;
import java.util.List;
public interface SightService {

    SimpleResponse createSight(SightRequest sightRequest);

    List<SightResponse> findAllSight();

    SightResponse findSightById(Long id);

    SimpleResponse updateSightById(Long id, SightRequest sightRequest);

    SimpleResponse deleteSightById(Long id);

    List<SightResponse> findAllSight(int currentPage, int pageSize);
}
