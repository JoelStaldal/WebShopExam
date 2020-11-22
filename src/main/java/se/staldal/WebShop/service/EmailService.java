package se.staldal.WebShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.model.User;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendConfirmationMail(User user, Order order) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("webshop@staldal.se");
        mailMessage.setTo("customer@user.com"); //user.getMail()
        mailMessage.setSubject("Order created successfully! Thank you, " + user.getName() + "!");
        mailMessage.setText(order.toString());

        javaMailSender.send(mailMessage);
    }
}
