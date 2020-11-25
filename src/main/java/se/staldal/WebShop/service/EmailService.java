package se.staldal.WebShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.model.User;

import javax.mail.MessagingException;


@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendConfirmationMail(User user, Order order) throws MessagingException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("webshop@staldal.se");
        mailMessage.setTo(user.getEmail()); //user.getMail()
        mailMessage.setSubject("Order created successfully! #" + order.getId());
        mailMessage.setText("Thank you! \n\nOrder details...");
        javaMailSender.send(mailMessage);
    }
}
