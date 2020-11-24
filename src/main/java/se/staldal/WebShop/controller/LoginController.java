package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.staldal.WebShop.controller.dto.UserRegistrationDto;
import se.staldal.WebShop.model.Role;
import se.staldal.WebShop.model.User;
import se.staldal.WebShop.service.UserSecurityService;
import se.staldal.WebShop.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    UserSecurityService userSecurityService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(HttpSession session, Model model) {
        return "home";
  /*      Optional<User> user = userService.getUserByEmail(email);
        if(user.isPresent()) {
            if(user.get().getRoles().equals("ROLES_ADMIN")) {
                return "redirect:/admin";
            } else {
                session.setAttribute("sessionUser", user.get());
                return "redirect:/home";
            }
        } else {
            model.addAttribute("incorrectUsername", true);
            return "login";
        }*/
    }

    @RequestMapping("/registration")
    public String showUserForm(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("user", userRegistrationDto);
        return "registration";
    }

    @PostMapping("/registration/create")
    public String createUser(@ModelAttribute("user") UserRegistrationDto registrationDto, Model model) {
        System.out.println(registrationDto);
        userSecurityService.save(registrationDto);
        return "redirect:/registration?success";

       /* System.out.println(user);
        if(userService.userExists(user.getEmail())) {
            model.addAttribute("userExists", true);
        } else {
            userService.save(new UserRegistrationDto(user.getEmail(), user.getPassword()));
            model.addAttribute("userExists", false);
            model.addAttribute("userCreated", true);
        }
        return "registration";*/
    }
}
