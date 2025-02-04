package travel.travel.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travel.travel.model.dto.request.TravelRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TravelResponse;
import travel.travel.service.TravelService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/travels")
public class TravelController {
    TravelService travelService;

    @PostMapping("/save")
    public ResponseEntity<SimpleResponse> createTravel(@RequestBody  TravelRequest travelRequest) {
        SimpleResponse response = travelService.createTravel(travelRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelResponse> getTravelById(@PathVariable Long id) {
        TravelResponse response = travelService.getTravelById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<SimpleResponse> updateTravelById(@PathVariable @Valid Long id, @RequestBody TravelRequest travelRequest) {
        SimpleResponse response = travelService.updateTravelById(id, travelRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleResponse> deleteTravelById(@PathVariable Long id) {
        SimpleResponse response = travelService.deleteTravelById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}