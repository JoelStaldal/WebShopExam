package se.staldal.WebShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import se.staldal.WebShop.model.Cart;
import se.staldal.WebShop.model.Product;

import javax.servlet.http.HttpSession;

@Service
public class CartService {

    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("shoppingCart");

        if(cart == null) {
            cart = new Cart();
            session.setAttribute("shoppingCart", cart);
        }

        return cart;
    }
}
