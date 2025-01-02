package travel.travel.validation.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation,String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email.endsWith("@gmail.com") ||
                email.endsWith("@mail.ru") ||
                email.endsWith("@yahoo.com") ||
                email.endsWith("@yandex.ru") ||
                email.endsWith("@aol.com") ||
                email.endsWith("@outlook.com") ||
                email.endsWith("@me.com") ||
                email.endsWith("@live.com") ||
                email.endsWith("@hotmail.com") ||
                email.endsWith("@msn.com") ||
                email.endsWith("@tutanota.com") ||
                email.endsWith("@icloud.com") ||
                email.endsWith("@gmx.com") ||
                email.endsWith("@zoho.com") ||
                email.endsWith("@qq.com") ||
                email.endsWith("@protonmail.com");
    }
}
