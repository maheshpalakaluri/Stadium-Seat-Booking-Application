package com.guvi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String to, String subject, String body) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);

		mailSender.send(message);
	}

	public void sendBookingConfirmation(String to, String userName, String eventName, String eventDateTime,
			String seats, Integer bookingId) {

		String body = "🏟️ StadiumBook - Booking Confirmed\n\n" + "Hi " + userName + ",\n\n"
				+ "Your booking has been confirmed.\n\n" + "Booking ID : #" + bookingId + "\n" + "Event      : "
				+ eventName + "\n" + "Date & Time: " + eventDateTime + "\n" + "Seats      : " + seats + "\n\n"
				+ "Thank you for booking with StadiumBook!";

		sendEmail(to, "🎟️ Booking Confirmation - StadiumBook", body);
	}

}
