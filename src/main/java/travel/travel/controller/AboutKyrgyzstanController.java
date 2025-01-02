package travel.travel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travel.travel.model.dto.request.AboutKyrgyzstanRequest;
import travel.travel.model.dto.response.AboutKyrgyzstanResponse;
import travel.travel.service.impl.AboutKyrgyzstanServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/about-kyrgyzstan")
public class AboutKyrgyzstanController {
    private final AboutKyrgyzstanServiceImpl aboutKyrgyzstanService;

    @PostMapping("/save")
    public ResponseEntity<AboutKyrgyzstanResponse> create(
            @RequestBody AboutKyrgyzstanRequest request) {
        AboutKyrgyzstanResponse response = aboutKyrgyzstanService.save(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/get_by_id")
    public ResponseEntity<AboutKyrgyzstanResponse> getById(
            @PathVariable Long id) {
        AboutKyrgyzstanResponse response = aboutKyrgyzstanService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<AboutKyrgyzstanResponse>> getAll() {
        List<AboutKyrgyzstanResponse> responses = aboutKyrgyzstanService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<AboutKyrgyzstanResponse> update(
            @PathVariable Long id,
            @RequestBody AboutKyrgyzstanRequest request) {
        AboutKyrgyzstanResponse response = aboutKyrgyzstanService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<AboutKyrgyzstanResponse> delete(
            @PathVariable Long id) {
        AboutKyrgyzstanResponse response = aboutKyrgyzstanService.delete(id);
        return ResponseEntity.ok(response);
    }
}
