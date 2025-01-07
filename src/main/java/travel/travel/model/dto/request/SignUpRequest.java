package travel.travel.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import travel.travel.validation.email.EmailValidation;
import travel.travel.validation.password.PasswordValidation;
import travel.travel.validation.phoneNumber.PhoneNumberValidation;

@Data
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "Name must not be empty")
    private String name;
    @EmailValidation
    private String email;
    @PasswordValidation
    private String password;
    @PhoneNumberValidation
    private String phoneNumber;
}
