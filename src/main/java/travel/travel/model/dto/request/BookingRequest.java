package travel.travel.model.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import travel.travel.validation.email.EmailValidation;
import travel.travel.validation.phoneNumber.PhoneNumberValidation;
import java.time.LocalDate;

public record BookingRequest(
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @EmailValidation(message = "Invalid email format")
        String email,
        @PhoneNumberValidation(message = "Invalid phone number format")
        String phoneNumber,
        @FutureOrPresent(message = "Travel dates must be in the future or present")
        LocalDate travelDates,
        int numberOfPeople,
        String message,
        Long tourId
) {
}