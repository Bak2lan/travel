package travel.travel.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travel.travel.model.dto.request.TravelRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TravelResponse;
import travel.travel.service.TravelService;

@Slf4j
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/travels")
public class TravelController {
    TravelService travelService;

    @GetMapping("/{id}")
    public ResponseEntity<TravelResponse> getTravelById(@PathVariable Long id) {
        TravelResponse response = travelService.getTravelById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<SimpleResponse> updateTravelById(@Valid  @RequestBody TravelRequest travelRequest) {
        SimpleResponse response = travelService.updateTravel(travelRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}