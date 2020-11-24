package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.staldal.WebShop.controller.dto.UserRegistrationDto;
import se.staldal.WebShop.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String showUserForm(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("user", userRegistrationDto);
        return "registration";
    }

    @PostMapping
    public String registerAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, Model model) {
        userService.save(registrationDto);
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
