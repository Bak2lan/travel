package travel.travel.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import travel.travel.model.enums.Role;
import travel.travel.validation.email.EmailValidation;
import travel.travel.validation.password.PasswordValidation;
import travel.travel.validation.phoneNumber.PhoneNumberValidation;

public record UserRequest(

        @NotBlank(message = "Name must not be empty")
        String name,
        @EmailValidation
        String email,
        @PasswordValidation
        String password,
        @PhoneNumberValidation
        String phoneNumber,
        Role role
) {
}
