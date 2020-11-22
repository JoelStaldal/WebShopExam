package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import se.staldal.WebShop.model.Cart;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.model.User;
import se.staldal.WebShop.service.OrderService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    OrderService orderService;

    @RequestMapping
    public String showCheckOutPage(HttpSession session) {
        return "checkout";
    }

    @RequestMapping("/submit")
    public String submitOrder(Model model) {
        model.addAttribute("confirmOrder", true);
        return "checkout";
    }

    @RequestMapping("/confirm")
    public String confirmOrder(HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        Cart cart = (Cart) session.getAttribute("shoppingCart");
        if(user != null && cart != null) {
            Order order = new Order(cart.getTotal(), user, cart.getItems());
            orderService.create(order);
            session.removeAttribute("shoppingCart");
        }
        return "checkout.confirm";
    }
}
