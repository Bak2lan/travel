package travel.travel.model.dto.request;

public record GetInTouchRequest(
        String fullName,
        String email,
        String phoneNumber,
        String message
) {
}