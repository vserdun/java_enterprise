package com.hillel.mvc.bank.controller;

import com.hillel.mvc.bank.models.UserEntity;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;
import com.hillel.mvc.bank.models.exceptions.UserValidationException;
import com.hillel.mvc.bank.services.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        List<UserEntity> users = bankService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String getAddUserForm(Model model) {
        model.addAttribute("userAttribute", new UserEntity());
        return "addUser";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public ModelAndView addUser(@ModelAttribute("userAttribute") UserEntity user) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            bankService.addUser(user);
            modelAndView.setViewName("redirect:/bank/users");
            return modelAndView;
        } catch (UserNotFoundException e) {
            return getErrorPage(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UserValidationException e) {
            return getErrorPage(HttpStatus.BAD_REQUEST, e.getViolations().toString());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public ModelAndView getEditUserForm(@RequestParam("id") int id, Model model) {
        try {
            UserEntity user = bankService.getUser(id);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("editUser");
            model.addAttribute("userAttribute", new UserEntity());
            return modelAndView;
        } catch (UserNotFoundException e) {
            return getErrorPage(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editUser/{id}")
    public ModelAndView getEditUserForm(@PathVariable("id") long id, @ModelAttribute("userAttribute") UserEntity user) {
        try {
            ModelAndView modelAndView = new ModelAndView();
            bankService.updateUser(id ,user);
            modelAndView.setViewName("redirect:/bank/users");
            return modelAndView;
        } catch (UserNotFoundException e) {
            return getErrorPage(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public ModelAndView deleteUser(@RequestParam("id") int id) {
        try {
            ModelAndView modelAndView = new ModelAndView();
            bankService.deleteUser(id);
            modelAndView.setViewName("redirect:/bank/users");
            return modelAndView;
        } catch (UserNotFoundException e) {
            return getErrorPage(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    private ModelAndView getErrorPage(HttpStatus status, String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("status", status.value());
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
