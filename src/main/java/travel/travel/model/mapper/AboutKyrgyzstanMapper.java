package travel.travel.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import travel.travel.model.dto.request.AboutKyrgyzstanRequest;
import travel.travel.model.dto.response.AboutKyrgyzstanResponse;
import travel.travel.model.entity.AboutKyrgyzstan;
import travel.travel.model.enums.AboutType;

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
        return AboutKyrgyzstanResponse.builder()
                .id(aboutKyrgyzstan.getId())
                .description(aboutKyrgyzstan.getDescription())
                .videoFile(aboutKyrgyzstan.getVideoFile())
                .name(aboutKyrgyzstan.getName())
                .images(aboutKyrgyzstan.getImages())
                .type(aboutKyrgyzstan.getType())
                .sighId(aboutKyrgyzstan.getId()).build();
    }
}
