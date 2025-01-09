package travel.travel.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpAndInResponse {
    private String email;
    private String role;
    private String token;
}
