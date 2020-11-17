package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.staldal.WebShop.model.Role;
import se.staldal.WebShop.model.User;
import se.staldal.WebShop.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping("/login/user")
    public String loginUser(@RequestParam("name") String name, HttpSession session, Model model) {
        Optional<User> user = userService.getUserByName(name);
        if(user.isPresent()) {
            if(user.get().getRole().equals(Role.ADMIN)) {
                return "redirect:/admin";
            } else {
                session.setAttribute("sessionUser", user.get());
                return "redirect:/products";
            }
        } else {
            model.addAttribute("incorrectUsername", true);
            return "login";
        }
    }

    @RequestMapping("/signup")
    public String showUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping("/signup/create")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        if(userService.userExists(user.getName())) {
            model.addAttribute("userExists", true);
        } else {
            userService.createUser(user);
            model.addAttribute("userExists", false);
            model.addAttribute("userCreated", true);
        }
        return "signup";
    }

    @RequestMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

}
