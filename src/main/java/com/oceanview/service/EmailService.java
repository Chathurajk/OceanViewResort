package com.oceanview.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService {

    private static final String FROM_EMAIL    = "yourhotel@gmail.com";  // ← Change කරන්න
    private static final String FROM_PASSWORD = "xxxx xxxx xxxx xxxx";  // ← Gmail App Password

    public static void sendReservationConfirmation(String toEmail, String guestName,
            String reservationNumber, String roomType, String checkIn,
            String checkOut, int nights, double totalAmount) {
        new Thread(() -> {
            try {
                Session session = createMailSession();
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(FROM_EMAIL, "Ocean View Resort"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                message.setSubject("Reservation Confirmed - " + reservationNumber);
                message.setContent(
                    "<html><body style='font-family:Arial;'>" +
                    "<div style='max-width:600px;margin:auto;background:white;border-radius:12px;overflow:hidden;'>" +
                    "<div style='background:#1a5276;color:white;padding:32px;text-align:center;'><h1>Ocean View Resort</h1></div>" +
                    "<div style='padding:32px;'><h2 style='color:#27ae60;'>Reservation Confirmed!</h2>" +
                    "<p>Dear <strong>" + guestName + "</strong>,</p>" +
                    "<p>Reservation: <strong style='color:#e74c3c;'>" + reservationNumber + "</strong></p>" +
                    "<p>Room: " + roomType + " | Check-In: " + checkIn + " | Check-Out: " + checkOut + "</p>" +
                    "<p>Nights: " + nights + " | <strong>Total: LKR " + String.format("%.2f", totalAmount) + "</strong></p>" +
                    "</div></div></body></html>",
                    "text/html; charset=utf-8");
                Transport.send(message);
                System.out.println("Email sent to: " + toEmail);
            } catch (Exception e) {
                System.err.println("Email failed: " + e.getMessage());
            }
        }).start();
    }

    public static void sendCancellationEmail(String toEmail, String guestName, String reservationNumber) {
        new Thread(() -> {
            try {
                Session session = createMailSession();
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(FROM_EMAIL, "Ocean View Resort"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                message.setSubject("Reservation Cancelled - " + reservationNumber);
                message.setContent("<html><body><h2>Reservation Cancelled</h2><p>Dear " + guestName +
                    ", your reservation " + reservationNumber + " has been cancelled.</p></body></html>",
                    "text/html; charset=utf-8");
                Transport.send(message);
            } catch (Exception e) {
                System.err.println("Cancellation email failed: " + e.getMessage());
            }
        }).start();
    }

    private static Session createMailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });
    }
}
