package travel.travel.service;

import jakarta.mail.MessagingException;
import travel.travel.model.dto.request.BookingRequest;
import travel.travel.model.dto.request.GetInTouchRequest;

public interface EmailService {
    void sendBookingToAdmin(BookingRequest bookingRequest);
    void sendBookingEmail(String to, String subject, String body) throws MessagingException;
    void sendGetInTouchMessage(GetInTouchRequest request);

}
