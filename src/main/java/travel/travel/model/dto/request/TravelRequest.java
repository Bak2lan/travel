package travel.travel.model.dto.request;

public record TravelRequest(
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