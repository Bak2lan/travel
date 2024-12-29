package travel.travel.model.dto.response;

import lombok.*;
import travel.travel.model.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    Long id;
    String name;
    String email;
    String password;
    String phoneNumber;
    Role role;
}
