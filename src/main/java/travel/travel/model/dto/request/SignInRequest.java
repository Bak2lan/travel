package travel.travel.model.dto.request;

import travel.travel.validation.email.EmailValidation;
import travel.travel.validation.password.PasswordValidation;

public record SignInRequest(
        @EmailValidation
        String email,
        @PasswordValidation
        String password
) {
}