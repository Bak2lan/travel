package travel.travel.model.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String role
) {
}
