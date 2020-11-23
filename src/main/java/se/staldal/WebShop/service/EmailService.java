package se.staldal.WebShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import se.staldal.WebShop.model.ConfirmationMail;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.model.Product;
import se.staldal.WebShop.model.User;

import java.util.ArrayList;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendConfirmationMail(User user, Order order) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("webshop@staldal.se");
        mailMessage.setTo("customer@user.com"); //user.getMail()
        mailMessage.setSubject("Order created successfully!");
        ConfirmationMail confirmationMail = new ConfirmationMail(order.getId().toString(), order.getOrderDate().toString(), user.getName());
        mailMessage.setText(confirmationMail.toString());

        javaMailSender.send(mailMessage);
    }
}
