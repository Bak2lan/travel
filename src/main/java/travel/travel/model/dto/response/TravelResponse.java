package travel.travel.model.dto.response;

public record TravelResponse(
        Long id,
        String aboutUsName,
        String aboutUs,
        String documentationName,
        String documentation,
        String sustainabilityName,
        String sustainability,
        String certificationName,
        String certification,
        String address,
        String phoneNumber,
        String email,
        String image
) {
}