package travel.travel.validation.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {PasswordValidator.class})
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidation {
    String message() default "Пароль должен содержать не менее 8 символов, включая цифры и специальные символы";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
