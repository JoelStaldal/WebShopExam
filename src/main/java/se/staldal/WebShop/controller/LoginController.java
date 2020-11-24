package se.staldal.WebShop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/redirect")
    public String redirectUserBasedOnRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().stream().findFirst().get().toString();

        if(role.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else {
            return "redirect:/home";
        }
    }
}
