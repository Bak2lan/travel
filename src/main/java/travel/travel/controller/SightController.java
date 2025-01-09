package travel.travel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import travel.travel.exception.NotFoundException;
import travel.travel.model.dto.request.SightRequest;
import travel.travel.model.dto.response.SightResponse;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.service.SightService;

import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sight")
@AllArgsConstructor
@Tag(name = "Sight API", description = "Sight API")
public class SightController {
    private final SightService sightService;

    @PostMapping("/save")
    @Operation(summary = "Save sight", description = "Save new sight by administrator")
    public SimpleResponse save(@RequestBody SightRequest request) throws NotFoundException {
        return sightService.createSight(request);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get by id sight", description = "Get by id sight by administrator")
    public SightResponse getById(@PathVariable Long id) throws NotFoundException {
        return sightService.findSightById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete sight", description = "Delete sight by administrator from database")
    public SimpleResponse deleteSightById(@PathVariable Long id) throws NotFoundException {
        return sightService.deleteSightById(id);
    }
    @PutMapping("/{id}/update")
    @Operation(summary = "Update sight", description = "Update sight by administrator from database")
    public SimpleResponse updateSight(@PathVariable Long id, @RequestBody SightRequest sightRequest) throws NotFoundException {
        return sightService.updateSightById(id, sightRequest);
    }

      @GetMapping("/getAll")
    @Operation(summary = "Get all sights", description = "Retrieve all sights")
    public List<SightResponse> getAll() throws NotFoundException {
        return sightService.findAllSight();
    }

}
