package com.hillel.mvc.bank.controller;

import com.hillel.mvc.bank.models.User;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;
import com.hillel.mvc.bank.models.exceptions.UserValidationException;
import com.hillel.mvc.bank.services.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/bank")
@Slf4j
public class UsersJspController {

    @Autowired
    private BankService bankService;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ModelAndView getUsers() {
        List<User> users = bankService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String getAddUserForm(Model model) {
        model.addAttribute("userAttribute", new User());
        return "addUser";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public ModelAndView addUser(@ModelAttribute("userAttribute") User user) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            bankService.addUser(user);
            modelAndView.setViewName("redirect:/bank/users");
            return modelAndView;
        } catch (UserNotFoundException e) {
            modelAndView.setViewName("404");
            return modelAndView;
        } catch (UserValidationException e) {
            modelAndView.setViewName("violentions");
            modelAndView.addObject("violentions", e.getViolations());
            return modelAndView;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public ModelAndView getEditUserForm(@RequestParam("id") int id, Model model) {
        try {
            User user = bankService.getUser(id);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("editUser");
            model.addAttribute("userAttribute", new User());
            return modelAndView;
        } catch (UserNotFoundException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("404");
            return modelAndView;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editUser/{id}")
    public String getEditUserForm(@PathVariable("id") long id, @ModelAttribute("userAttribute") User user) {
        try {
            log.info("id =-> {}", id);
            bankService.updateUser(id ,user);
            return "redirect:/bank/users";
        } catch (UserNotFoundException e) {
            return "404";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public String deleteUser(@RequestParam("id") int id) {
        try {
            bankService.deleteUser(id);
            return "redirect:/bank/users";
        } catch (UserNotFoundException e) {
            return "404";
        }
    }
}
