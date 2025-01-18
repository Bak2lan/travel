package travel.travel.service;

import jakarta.mail.MessagingException;
import travel.travel.model.dto.request.BookingRequest;
import travel.travel.model.dto.request.GetInTouchRequest;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendBookingToAdmin(BookingRequest bookingRequest);
    void sendGetInTouchMessage(GetInTouchRequest request);

}
