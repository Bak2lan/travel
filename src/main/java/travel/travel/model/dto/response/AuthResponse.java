package travel.travel.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String email;
    private String role;
    private JwtTokenResponse token;
    private String message;
    private HttpStatus status;

}