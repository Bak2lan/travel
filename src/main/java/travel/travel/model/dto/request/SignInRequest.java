package travel.travel.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import travel.travel.validation.email.EmailValidation;
import travel.travel.validation.password.PasswordValidation;
@Data
@AllArgsConstructor
public class SignInRequest {
    @EmailValidation
    private String username;
    @PasswordValidation
    private String password;
}
