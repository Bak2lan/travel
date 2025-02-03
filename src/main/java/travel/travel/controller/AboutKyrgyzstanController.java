package travel.travel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import travel.travel.model.dto.request.AboutKyrgyzstanRequest;
import travel.travel.model.dto.response.AboutKyrgyzstanResponse;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.service.impl.AboutKyrgyzstanServiceImpl;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/about-kyrgyzstan")
public class AboutKyrgyzstanController {

    private final AboutKyrgyzstanServiceImpl aboutKyrgyzstanService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/save")
    public AboutKyrgyzstanResponse create(@RequestBody AboutKyrgyzstanRequest request) {
        return aboutKyrgyzstanService.save(request);

    }

    @GetMapping("/{id}/get_by_id")
    public AboutKyrgyzstanResponse getById(@PathVariable Long id) {
        return aboutKyrgyzstanService.findById(id);

    }

    @GetMapping("/get_all")
    public List<AboutKyrgyzstanResponse> getAll() {
        return aboutKyrgyzstanService.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}/update")
    public AboutKyrgyzstanResponse update(@PathVariable Long id, @RequestBody AboutKyrgyzstanRequest request) {
        return aboutKyrgyzstanService.update(id, request);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}/delete")
    public SimpleResponse delete(@PathVariable Long id) {
        return aboutKyrgyzstanService.delete(id);

    }


}



