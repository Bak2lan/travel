package travel.travel.service.impl;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import travel.travel.exception.NotFoundException;
import travel.travel.model.dto.request.BookingRequest;
import travel.travel.model.dto.request.GetInTouchRequest;
import travel.travel.model.entity.Tour;
import travel.travel.repository.TourRepository;
import travel.travel.service.EmailService;

import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TourRepository tourRepository;
    @Value("${spring.mail.username}")
    private String senderEmail;
    @Value("${app.email.sender.name}")
    private String senderName;
    @Value("${app.admin.email}")
    private String adminEmail;
    public EmailServiceImpl(JavaMailSender mailSender, TourRepository tourRepository) {
        this.mailSender = mailSender;
        this.tourRepository = tourRepository;
    }

    @Override
    public void sendBookingToAdmin(BookingRequest bookingRequest) {
        try {
            Tour tour = tourRepository.findById(bookingRequest.tourId())
                    .orElseThrow(() -> new NotFoundException("Tour not found for id: " + bookingRequest.tourId()));

            String to = adminEmail;
            String subject = "New Booking from " + bookingRequest.firstName();
            String body = buildBookingEmailBody(bookingRequest, tour);
            sendEmail(to, subject, body);
            log.info("Booking email sent successfully to admin for tour: {}", tour.getTourName());
        } catch (Exception e) {
            log.error("Error while sending booking email to admin: {}", e.getMessage());
            throw new RuntimeException("Error sending booking email", e);
        }
    }

    @Override
    public void sendGetInTouchMessage(GetInTouchRequest request) {
        try {
            String to = adminEmail;
            String subject = "New Message from " + request.fullName();
            String body = buildGetInTouchEmailBody(request);

            sendEmail(to, subject, body);
            log.info("Get In Touch message sent successfully for: {}", request.fullName());
        } catch (Exception e) {
            log.error("Error while sending Get In Touch email: {}", e.getMessage());
            throw new RuntimeException("Error sending Get In Touch email", e);
        }
    }

    private void sendEmail(String to, String subject, String body) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(new InternetAddress(senderEmail, senderName));
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(message);
    }

    private String buildBookingEmailBody(BookingRequest bookingRequest, Tour tour) {
        return "<p>A new booking has been made:</p>" +
                "<p><b>Name:</b> " + bookingRequest.firstName() + " " + bookingRequest.lastName() + "</p>" +
                "<p><b>Email:</b> " + bookingRequest.email() + "</p>" +
                "<p><b>Phone:</b> " + bookingRequest.phoneNumber() + "</p>" +
                "<p><b>Travel Dates:</b> " + bookingRequest.travelDates() + "</p>" +
                "<p><b>Number of People:</b> " + bookingRequest.numberOfPeople() + "</p>" +
                "<p><b>Message:</b> " + bookingRequest.message() + "</p>" +
                "<p><b>Tour:</b> " + tour.getTourName() + " (ID: " + tour.getId() + ")</p>";
    }

    private String buildGetInTouchEmailBody(GetInTouchRequest request) {
        return "<p>A new message has been received:</p>" +
                "<p><b>Name:</b> " + request.fullName() + "</p>" +
                "<p><b>Email:</b> " + request.email() + "</p>" +
                "<p><b>Phone:</b> " + request.phoneNumber() + "</p>" +
                "<p><b>Message:</b> " + request.message() + "</p>";
    }
}
