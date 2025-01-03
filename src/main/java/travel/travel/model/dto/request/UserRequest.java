package travel.travel.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import travel.travel.model.enums.Role;
import travel.travel.validation.email.EmailValidation;
import travel.travel.validation.password.PasswordValidation;
import travel.travel.validation.phoneNumber.PhoneNumberValidation;

public record UserRequest(

        @NotBlank(message = "Name must not be empty")
        String name,
        @EmailValidation(message = "Некорректный формат email")
        String email,
        @PasswordValidation(message = "Пароль должен содержать не менее 8 символов, включая цифры и специальные символы")
        String password,
        @PhoneNumberValidation(message =  " Некорректный формат номера телефона")
        String phoneNumber,
        Role role
) {
}
