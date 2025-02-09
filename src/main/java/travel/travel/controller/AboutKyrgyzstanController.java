package travel.travel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import travel.travel.model.dto.request.AboutKyrgyzstanRequest;
import travel.travel.model.dto.response.AboutKyrgyzstanImagesResponse;
import travel.travel.model.dto.response.AboutKyrgyzstanResponse;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.repository.AboutKyrgyzstanRepository;
import travel.travel.service.impl.AboutKyrgyzstanServiceImpl;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequiredArgsConstructor
@RequestMapping("/api/about-kyrgyzstan")
public class AboutKyrgyzstanController {

    private final AboutKyrgyzstanServiceImpl aboutKyrgyzstanService;
    private final AboutKyrgyzstanRepository aboutKyrgyzstanRepository;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/save")
    public AboutKyrgyzstanResponse create(@RequestBody AboutKyrgyzstanRequest request) {
        return aboutKyrgyzstanService.save(request);

    }

    @GetMapping("/{id}")
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

    @GetMapping("/getCulture")
    public ResponseEntity<List<AboutKyrgyzstanResponse>>getCulture(){
        List<AboutKyrgyzstanResponse> cultura = aboutKyrgyzstanService.getCultura();
        return ResponseEntity.ok(cultura);
    }

    @GetMapping("/getTradition")
    public ResponseEntity<List<AboutKyrgyzstanResponse>>getTradition(){
        List<AboutKyrgyzstanResponse> tradition = aboutKyrgyzstanService.getTradition();
        return ResponseEntity.ok(tradition);
    }

    @GetMapping("/getHistoricalPlaces")
    public ResponseEntity<List<AboutKyrgyzstanResponse>>getHistoricalPlaces(){
        List<AboutKyrgyzstanResponse> historicalPlaces = aboutKyrgyzstanService.getHistoricalPlaces();
        return ResponseEntity.ok(historicalPlaces);
    }

    @GetMapping("/about-kyrgyzstanImage")
    public List<AboutKyrgyzstanImagesResponse> getAboutKyrgyzstanImages(
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(required = false) Integer pageSize) {
        if (pageSize == null || pageSize <= 0) {
            long totalRecords = aboutKyrgyzstanRepository.count();
            pageSize = (int) totalRecords;
        }

        return aboutKyrgyzstanService.aboutKyrgyzstan(currentPage, pageSize);
    }


}



