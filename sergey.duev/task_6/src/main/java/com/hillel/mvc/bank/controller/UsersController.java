package com.hillel.mvc.bank.controller;

import com.hillel.mvc.bank.model.User;
import com.hillel.mvc.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/bank/users")
public class UsersController {

    @Autowired
    private BankService bankService;

    @RequestMapping(method = RequestMethod.GET)
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
    public String addUser(@ModelAttribute("userAttribute") User user) {
        bankService.addUser(user);
        return "redirect:/bank/users";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public ModelAndView getEditUserForm(@RequestParam("id") int id, Model model) {
        User users = bankService.getUser(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", users);
        modelAndView.setViewName("editUser");
        model.addAttribute("userAttribute", new User());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editUser")
    public String getEditUserForm(@ModelAttribute("userAttribute") User user) {
        bankService.updateUser(user);
        return "redirect:/bank/users";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public String deleteUser(@RequestParam("id") int id) {
        bankService.deleteUser(id);
        return "redirect:/bank/users";
    }
}
