package travel.travel.model.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import travel.travel.validation.email.EmailValidation;
import travel.travel.validation.password.PasswordValidation;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    Long id;
    String name;
    @EmailValidation
    String email;
    @PasswordValidation
    String password;
    @PasswordValidation
    String phoneNumber;
}
