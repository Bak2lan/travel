package travel.travel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import travel.travel.model.dto.request.TourRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TourGetAllResponse;
import travel.travel.model.dto.response.TourResponseGetByID;
import travel.travel.service.TourService;

import java.util.List;

@Tag(name = "REST APIs for Tour in Tourism",
        description = "CRUD APIs to CREATE, READ, UPDATE, DELETE tour details")
@RestController@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/api/tours")
@Slf4j
public class TourController {
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(
            summary = "Save Tour",
            description = "Save tour by administrator to database"
    )
    @PostMapping("/save")
    public ResponseEntity<SimpleResponse> saveTour(@RequestBody TourRequest tourRequest) {
        SimpleResponse simpleResponse = tourService.saveTour(tourRequest);
        return ResponseEntity.status(simpleResponse.getStatus()).body(simpleResponse);
    }

    @Operation(
            summary = "Get All Tours",
            description = "Get all tours with pagination from database"
    )
    @GetMapping("/getAllTours")
    public List<TourGetAllResponse> getAll() {
        return tourService.getAllTour();

    }

    @GetMapping("/getAllToursByPopular")
    public List<TourGetAllResponse>getAllTourByPopular(){
     return tourService.getAllTourByPopular();

    }

    @Operation(
            summary = "Get Tour By ID",
            description = "Get tour by id from database"
    )
    @GetMapping("/getById/{id}")
    public ResponseEntity<TourResponseGetByID> getById(@PathVariable Long id) {
        TourResponseGetByID tourById = tourService.getTourById(id);
        return ResponseEntity.ok(tourById);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(
            summary = "Update Tour",
            description = "Update tour by administrator from database"
    )
    @PutMapping("/updateTour/{id}")
    public SimpleResponse updateTour(@PathVariable Long id,
                                     @RequestBody TourRequest tourRequest) {
        return tourService.updateTour(id, tourRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(
            summary = "Delete Tour",
            description = "Delete tour by administrator from database "
    )
    @DeleteMapping("/deleteTour/{id}")
    public ResponseEntity<SimpleResponse> deleteTour(@PathVariable Long id) {
        SimpleResponse simpleResponse = tourService.deleteTour(id);
        return ResponseEntity.status(simpleResponse.getStatus()).body(simpleResponse);
    }
}