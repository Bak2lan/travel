package travel.travel.controller;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.travel.model.dto.request.BookingRequest;
import travel.travel.model.dto.request.GetInTouchRequest;
import travel.travel.service.EmailService;

@RestController
@RequestMapping("/api/bookings")
@Validated
public class ContactController {
    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;

    }
    @PostMapping("/get-in-touch")
    public ResponseEntity<String> sendGetInTouchMessage(@Valid @RequestBody GetInTouchRequest getInTouchRequest) {
        emailService.sendGetInTouchMessage(getInTouchRequest);
        return ResponseEntity.ok("Message sent successfully!");
    }
    @PostMapping("/booking")
    public ResponseEntity<String> sendBookingToAdmin(@RequestBody BookingRequest bookingRequest) {
        emailService.sendBookingToAdmin(bookingRequest);
        return ResponseEntity.ok("Booking sent successfully!");
    }
}
