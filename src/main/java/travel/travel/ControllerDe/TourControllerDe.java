package travel.travel.ControllerDe;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/api/toursDe")
@Slf4j
@RequiredArgsConstructor
public class TourControllerDe {

    private final TourService tourService;


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(
            summary = "Save Tour",
            description = "Save tour by administrator to database"
    )
    @PostMapping("/save")
    public ResponseEntity<SimpleResponse> saveTour(@RequestBody TourRequest tourRequest) {
        SimpleResponse simpleResponse = tourService.saveTourDe(tourRequest);
        return ResponseEntity.status(simpleResponse.getStatus()).body(simpleResponse);
    }

    @Operation(
            summary = "Get All Tours",
            description = "Get all tours with pagination from database"
    )
    @GetMapping("/getAllTours")
    public List<TourGetAllResponse> getAll() {
        return tourService.getAllTourDe();

    }

    @GetMapping("/getAllToursByPopular")
    public List<TourGetAllResponse>getAllTourByPopular(){
        return tourService.getAllTourByPopularDe();

    }

    @Operation(
            summary = "Get Tour By ID",
            description = "Get tour by id from database"
    )
    @GetMapping("/getById/{id}")
    public ResponseEntity<TourResponseGetByID> getById(@PathVariable Long id) {
        TourResponseGetByID tourById = tourService.getTourByIdDe(id);
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
        return tourService.updateTourDe(id, tourRequest);
    }
}