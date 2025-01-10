package travel.travel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import travel.travel.model.dto.request.TourRequest;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.dto.response.TourPaginationResponse;
import travel.travel.model.dto.response.TourResponseGetByID;
import travel.travel.service.TourService;

@Tag(name = "REST APIs for Tour in Tourism",
        description = "CRUD APIs to CREATE, READ, UPDATE, DELETE tour details")
@RestController
@RequestMapping("/api/tours")
public class TourController {
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(
            summary = "Save Tour",
            description = "Save tour by administrator to database"
    )
    @PostMapping("/save")
    public ResponseEntity<SimpleResponse> saveTour(@RequestBody TourRequest tourRequest) {
        SimpleResponse simpleResponse = tourService.saveTour(tourRequest);
        return ResponseEntity.status(simpleResponse.getStatus()).body(simpleResponse);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @Operation(
            summary = "Get All Tours",
            description = "Get all tours from database"
    )
    @GetMapping("/getAll")
    public ResponseEntity<TourPaginationResponse> getAll(@RequestParam(defaultValue = "1") int currentPage,
                                                         @RequestParam(defaultValue = "4") int pageSize) {
        TourPaginationResponse allTour = tourService.getAllTour(currentPage, pageSize);
        return ResponseEntity.ok(allTour);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
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
    public ResponseEntity<TourResponseGetByID> updateTour(@PathVariable Long id,
                                                          @RequestBody TourRequest tourRequest) {
        TourResponseGetByID tourResponseGetByID = tourService.updateTour(id, tourRequest);
        return ResponseEntity.ok(tourResponseGetByID);
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
