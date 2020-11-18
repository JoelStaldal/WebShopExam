package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.staldal.WebShop.model.Cart;
import se.staldal.WebShop.model.Product;
import se.staldal.WebShop.service.CartService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping
    public String showCartPage() {
        return "cart";
    }

    @RequestMapping("/add")
    public String addProduct(HttpSession session, @RequestParam("id") Product product) {
        Cart cart = cartService.getCart(session);
        cart.addItem(product);
        return "cart";
    }

    @RequestMapping("/update")
    public String updateQuantity(HttpSession session, @RequestParam("id") Product product, @RequestParam("quantity") int quantity) {
        Cart cart = cartService.getCart(session);
        cart.updateItem(product, quantity);
        return "cart";
    }

    @RequestMapping("/remove")
    public String remove(HttpSession session, @RequestParam("id") Product product){
        Cart cart = cartService.getCart(session);
        cart.removeItem(product);
        return "cart";
    }
}
