package travel.travel.validation.phoneNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation,String> {
    private static final String KYRGYZ_PHONE_PATTERN = "^\\+996(50[0-9]|55[0-9]|70[0-9]|77[0-9]|99[0-9]|22[0-9])[0-9]{6}$";
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return false;
        }
        return phoneNumber.matches(KYRGYZ_PHONE_PATTERN);
    }
}