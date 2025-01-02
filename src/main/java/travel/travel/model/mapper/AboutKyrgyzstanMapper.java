package travel.travel.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import travel.travel.model.dto.request.AboutKyrgyzstanRequest;
import travel.travel.model.dto.response.AboutKyrgyzstanResponse;
import travel.travel.model.entity.AboutKyrgyzstan;

@Component
@RequiredArgsConstructor
public class AboutKyrgyzstanMapper {
    public AboutKyrgyzstan mapToEntity(AboutKyrgyzstanRequest aboutKyrgyzstanRequest) {
        AboutKyrgyzstan aboutKyrgyzstan = new AboutKyrgyzstan();
        aboutKyrgyzstan.setDescription(aboutKyrgyzstanRequest.getDescription());
        aboutKyrgyzstan.setVideoFile(aboutKyrgyzstanRequest.getVideoFile());
        aboutKyrgyzstan.setName(aboutKyrgyzstanRequest.getName());
        aboutKyrgyzstan.setImages(aboutKyrgyzstanRequest.getImages());
        aboutKyrgyzstan.setType(aboutKyrgyzstanRequest.getType());
        return aboutKyrgyzstan;
    }

    public AboutKyrgyzstanResponse mapToResponse(AboutKyrgyzstan aboutKyrgyzstan) {
        AboutKyrgyzstanResponse response = new AboutKyrgyzstanResponse();
        response.setId(aboutKyrgyzstan.getId());
        response.setDescription(aboutKyrgyzstan.getDescription());
        response.setVideoFile(aboutKyrgyzstan.getVideoFile());
        response.setName(aboutKyrgyzstan.getName());
        response.setImages(aboutKyrgyzstan.getImages());
        response.setType(aboutKyrgyzstan.getType());
        response.setSighId(aboutKyrgyzstan.getSight() != null ? aboutKyrgyzstan.getSight().getId() : null);
        return response;
    }
}
