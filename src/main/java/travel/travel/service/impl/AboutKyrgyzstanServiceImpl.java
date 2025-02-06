package travel.travel.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import travel.travel.exception.NotFoundException;
import travel.travel.model.dto.request.AboutKyrgyzstanRequest;
import travel.travel.model.dto.response.AboutKyrgyzstanResponse;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.model.entity.AboutKyrgyzstan;
import travel.travel.model.entity.Sight;
import travel.travel.model.mapper.AboutKyrgyzstanMapper;
import travel.travel.repository.AboutKyrgyzstanRepository;
import travel.travel.repository.SightRepository;
import travel.travel.service.ServiceLayer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AboutKyrgyzstanServiceImpl implements ServiceLayer<AboutKyrgyzstanRequest, AboutKyrgyzstanResponse> {

    private final AboutKyrgyzstanRepository aboutKyrgyzstanRepository;
    private final AboutKyrgyzstanMapper aboutMapper;

    public AboutKyrgyzstanServiceImpl(AboutKyrgyzstanRepository aboutKyrgyzstanRepository, AboutKyrgyzstanMapper aboutMapper) {
        this.aboutKyrgyzstanRepository = aboutKyrgyzstanRepository;
        this.aboutMapper = aboutMapper;
    }

    @Override
    public AboutKyrgyzstanResponse save(AboutKyrgyzstanRequest aboutKyrgyzstanRequest) {
        AboutKyrgyzstan aboutKyrgyzstan = aboutMapper.mapToEntity(aboutKyrgyzstanRequest);
        return aboutMapper.mapToResponse(aboutKyrgyzstanRepository.save(aboutKyrgyzstan));
    }


    @Override
    public AboutKyrgyzstanResponse findById(Long id) {
        AboutKyrgyzstan aboutKyrgyzstan = byId(id);
        return aboutMapper.mapToResponse(aboutKyrgyzstan);
    }

    @Override
    public List<AboutKyrgyzstanResponse> findAll() {
        return aboutKyrgyzstanRepository.findAll().stream()
                .map(aboutMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AboutKyrgyzstanResponse update(Long id, AboutKyrgyzstanRequest aboutKyrgyzstanRequest) {
        AboutKyrgyzstan aboutKyrgyzstan = byId(id);
        if (aboutKyrgyzstanRequest.getDescription() != null) {
            aboutKyrgyzstan.setDescription(aboutKyrgyzstanRequest.getDescription());
        }
        if (aboutKyrgyzstanRequest.getImages() != null) {
            aboutKyrgyzstan.setImages(aboutKyrgyzstanRequest.getImages());
        }
        if (aboutKyrgyzstanRequest.getName() != null) {
            aboutKyrgyzstan.setName(aboutKyrgyzstanRequest.getName());
        }
        if (aboutKyrgyzstanRequest.getType() != null) {
            aboutKyrgyzstan.setType(aboutKyrgyzstanRequest.getType());
        }
        return aboutMapper.mapToResponse(aboutKyrgyzstanRepository.save(aboutKyrgyzstan));
    }

    @Override
    public SimpleResponse delete(Long id) {
        try {
            AboutKyrgyzstan aboutKyrgyzstan = byId(id);
            aboutKyrgyzstanRepository.delete(aboutKyrgyzstan);
            return SimpleResponse.builder().message("deleted").status(HttpStatus.OK).timestamp(LocalDateTime.now()).build();

        } catch (Exception ex) {
            throw new RuntimeException("Не удалось удалить AboutKyrgyzstan с id: " + id, ex);
        }
    }

    @Override
    public List<AboutKyrgyzstanResponse> getCultura() {
        List<AboutKyrgyzstan> culture = aboutKyrgyzstanRepository.getCulture();
        return culture.stream()
                .map(aboutKyrgyzstan -> AboutKyrgyzstanResponse
                        .builder()
                        .id(aboutKyrgyzstan.getId())
                        .description(aboutKyrgyzstan.getDescription())
                        .videoFile(aboutKyrgyzstan.getVideoFile())
                        .name(aboutKyrgyzstan.getName())
                        .images(aboutKyrgyzstan.getImages())
                        .type(aboutKyrgyzstan.getType()).build()).toList();
    }

    @Override
    public List<AboutKyrgyzstanResponse> getTradition() {
        List<AboutKyrgyzstan> tradition = aboutKyrgyzstanRepository.getTradition();
        return tradition.stream()
                .map(aboutKyrgyzstan -> AboutKyrgyzstanResponse
                        .builder()
                        .id(aboutKyrgyzstan.getId())
                        .description(aboutKyrgyzstan.getDescription())
                        .videoFile(aboutKyrgyzstan.getVideoFile())
                        .name(aboutKyrgyzstan.getName())
                        .images(aboutKyrgyzstan.getImages())
                        .type(aboutKyrgyzstan.getType()).build()).toList();
    }

    @Override
    public List<AboutKyrgyzstanResponse> getHistoricalPlaces() {
        List<AboutKyrgyzstan> historicalPlaces = aboutKyrgyzstanRepository.getHistoricalPlaces();
        return historicalPlaces.stream()
                .map(aboutKyrgyzstan -> AboutKyrgyzstanResponse
                        .builder()
                        .id(aboutKyrgyzstan.getId())
                        .description(aboutKyrgyzstan.getDescription())
                        .videoFile(aboutKyrgyzstan.getVideoFile())
                        .name(aboutKyrgyzstan.getName())
                        .images(aboutKyrgyzstan.getImages())
                        .type(aboutKyrgyzstan.getType()).build()).toList();
    }

    AboutKyrgyzstan byId(Long id) {
        return aboutKyrgyzstanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("AboutKyrgyzstan with id: " + id + " not found!"));
    }
}
