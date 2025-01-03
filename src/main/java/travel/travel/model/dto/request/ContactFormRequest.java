package travel.travel.model.dto.request;
public record ContactFormRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String travelDates,
        int numberOfPeople,
        String message
) {
}
