package travel.travel.model.dto.request;

public record TravelRequest(
        String aboutUs,
        String documentation,
        String sustainability,
        String certification,
        String address,
        String phoneNumber,
        String email,
        String image
) {
}