package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.staldal.WebShop.model.User;
import se.staldal.WebShop.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/redirect")
    public String redirectUserBasedOnRole(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().stream().findFirst().get().toString();

        if(role.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else {
            String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<User> user = userService.getUserByEmail(userEmail);
            if(user.isPresent()) {
                session.setAttribute("sessionUser", user.get());
            }
            return "redirect:/home";
        }
    }
}
