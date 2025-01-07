package travel.travel.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import travel.travel.validation.email.EmailValidation;

@Data
@AllArgsConstructor
public class SignInRequest {
    @EmailValidation
    private String username;
    private String password;
}