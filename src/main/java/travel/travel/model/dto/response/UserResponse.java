package travel.travel.model.dto.response;

import lombok.Builder;
import travel.travel.model.enums.Role;

@Builder
public record UserResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        Role role
) {
}
