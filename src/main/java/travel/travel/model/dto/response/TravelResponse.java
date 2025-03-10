package travel.travel.model.dto.response;

public record TravelResponse(
        Long id,
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