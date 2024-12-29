package travel.travel.model.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseForGetAll {
    Long id;
    String name;
    String email;
    String password;
    String phoneNumber;
}
