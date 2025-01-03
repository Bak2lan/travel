package travel.travel.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation,String> {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$";
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password !=null && password.matches(PASSWORD_PATTERN);
    }
}
