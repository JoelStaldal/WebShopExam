package se.staldal.WebShop.component;

import org.springframework.stereotype.Component;
import se.staldal.WebShop.model.Cart;

import javax.servlet.http.HttpSession;

@Component
public class CartComponent {

    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("shoppingCart");

        if(cart == null) {
            cart = new Cart();
            session.setAttribute("shoppingCart", cart);
        }
        return cart;
    }
}
