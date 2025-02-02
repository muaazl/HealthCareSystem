package util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailServices {

    private final String username; // Your email username
    private final String password; // Your email password

    public EmailServices() {
        // Set these values to your email configuration
        this.username = "your-email@example.com";
        this.password = "your-email-password";
    }

    public void sendEmail(String to, String subject, String body) {
        // Set up the mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.example.com"); // SMTP server address
        properties.put("mail.smtp.port", "587"); // SMTP port (587 for TLS)

        // Get the default Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set the From, To, Subject, and Body
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully to " + to);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email to " + to);
        }
    }
}
