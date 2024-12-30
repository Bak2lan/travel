package travel.travel.validation.phoneNumber;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {PhoneNumberValidator.class})

@Target({ElementType.METHOD,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.ANNOTATION_TYPE,ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberValidation {
    String message() default "{Your phone number must start with +996 and contain 13 digits}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
