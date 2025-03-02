package travel.travel.model.dto.request;

public record TravelRequest(
        String aboutUs,
        String documentation,
        String sustainability,
        String address,
        String phoneNumber,
        String email,
        String image
) {
}