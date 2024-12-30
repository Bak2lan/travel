package travel.travel.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import travel.travel.model.dto.request.UserRequest;
import travel.travel.model.dto.response.UserResponse;
import travel.travel.model.entity.User;
import travel.travel.model.enums.Role;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "role", target = "role")
    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(UserRequest userRequest, @MappingTarget User user);

    default Role mapRole(String role) {
        if (role == null) {
            return Role.ROLE_USER;
        }
        try {
            return Role.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + role);
        }


    }
}