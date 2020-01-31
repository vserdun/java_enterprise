package com.hillel.bankserviceboot.controller;

import com.hillel.bankserviceboot.model.UserEntity;
import com.hillel.bankserviceboot.service.SecurityService;
import com.hillel.bankserviceboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserEntity());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserEntity userEntity, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.addUser(userEntity);
        securityService.autoLogin(userEntity.getEmail(), userEntity.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password invalid");
        }

        if (logout != null) {
            model.addAttribute("message", "Your have been logged out successfully");
        }

        return "login";
    }

    @GetMapping({"/", "/index"})
    public String welcome() {
        return "index";
    }

}
