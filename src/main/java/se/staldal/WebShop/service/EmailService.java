package se.staldal.WebShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.model.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendConfirmationMail(User user, Order order) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("webshop@staldal.se");
            helper.setTo(user.getEmail());
            helper.setSubject("Order confirmation! #" + order.getId());
            message.setContent("<h2>Thank you!</h2>\n\n<p>Order details...</p>", "text/html");
        } catch (MessagingException e) {
            throw new RuntimeException("Sending email problem: " + e);
        }
        javaMailSender.send(message);
    }
}
