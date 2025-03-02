package travel.travel.model.dto.request;

import travel.travel.validation.email.EmailValidation;
import travel.travel.validation.password.PasswordValidation;
import travel.travel.validation.phoneNumber.PhoneNumberValidation;

public record SignUpRequest(
        String fullName,
        @PhoneNumberValidation
        String phoneNumber,
        @EmailValidation
        String email,
        @PasswordValidation
        String password
) {
}