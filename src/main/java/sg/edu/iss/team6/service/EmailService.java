package sg.edu.iss.team6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String recipientEmail, String confirmationLink) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("sa56team6@outlook.com","Course Registration");
            helper.setTo(recipientEmail);
            helper.setSubject("Confirmation Email");

            String htmlContent = "<p>Please click the link below to confirm your enrollment:</p>" +
                    "<p><a href=\"" + confirmationLink + "\">Confirm enrollment</a></p>";
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            // Handle exception
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


}
