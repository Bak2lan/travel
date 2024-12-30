package travel.travel.model.dto.response;

import travel.travel.model.enums.Role;

public record UserResponse(
        Long id,
        String name,
        String email,
        String password,
        String phoneNumber,
        Role role
) {
}
