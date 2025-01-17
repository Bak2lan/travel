package travel.travel.service.impl;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

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

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TourRepository tourRepository;

    public EmailServiceImpl(JavaMailSender mailSender, TourRepository tourRepository) {
        this.mailSender = mailSender;
        this.tourRepository = tourRepository;
    }
    @Override
    public void sendBookingToAdmin(BookingRequest bookingRequest) {
        try {
            Tour tour = tourRepository.findById(bookingRequest.tourId())
                    .orElseThrow(() -> new NotFoundException("Tour not found for id: " + bookingRequest.tourId()));
            String to = "beksonss1@gmail.com";
            String subject = "New Booking from " + bookingRequest.firstName();
            StringBuilder body = new StringBuilder();
            body.append("<p>A new booking has been made:</p>")
                    .append("<p><b>Name:</b> ").append(bookingRequest.firstName()).append(" ").append(bookingRequest.lastName()).append("</p>")
                    .append("<p><b>Email:</b> ").append(bookingRequest.email()).append("</p>")
                    .append("<p><b>Phone:</b> ").append(bookingRequest.phoneNumber()).append("</p>")
                    .append("<p><b>Travel Dates:</b> ").append(bookingRequest.travelDates()).append("</p>")
                    .append("<p><b>Number of People:</b> ").append(bookingRequest.numberOfPeople()).append("</p>")
                    .append("<p><b>Message:</b> ").append(bookingRequest.message()).append("</p>")
                    .append("<p><b>Tour:</b> ").append(tour.getTourName()).append(" (ID: ").append(tour.getId()).append(")</p>");
            sendBookingEmail(to, subject, body.toString());
            log.info("Booking email sent successfully to admin for tour {}", tour.getTourName());
        } catch (MailException | MessagingException e) {
            log.error("Error while sending booking email to admin: {}", e.getMessage());
            throw new RuntimeException("Error sending email", e);
        }
    }

    @Override
    public void sendGetInTouchMessage(GetInTouchRequest request) {
        try {
            String to = "beksonss1@gmail.com";
            String subject = "New Message from " + request.fullName();
            StringBuilder body = new StringBuilder();
            body.append("<p>A new message has been received:</p>")
                    .append("<p><b>Name:</b> ").append(request.fullName()).append("</p>")
                    .append("<p><b>Email:</b> ").append(request.email()).append("</p>")
                    .append("<p><b>Phone:</b> ").append(request.phoneNumber()).append("</p>")
                    .append("<p><b>Message:</b> ").append(request.message()).append("</p>");
            sendEmail(to, subject, body.toString());
            log.info("Get In Touch message sent successfully for {}", request.fullName());
        } catch (MailException | MessagingException e) {
            log.error("Error while sending Get In Touch email: {}", e.getMessage());
            throw new RuntimeException("Error sending email", e);
        }
    }
    private void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        mailSender.send(message);

    }
    @Override
    public void sendBookingEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        mailSender.send(message);
    }
}