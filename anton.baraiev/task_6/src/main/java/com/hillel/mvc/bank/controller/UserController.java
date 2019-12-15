package com.hillel.mvc.bank.controller;

import com.hillel.mvc.bank.model.BankAccount;
import com.hillel.mvc.bank.model.User;
import com.hillel.mvc.bank.service.account_service.AccountOperationModeService;
import com.hillel.mvc.bank.service.user_service.UserOperationModeService;
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
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserOperationModeService userService;

    @Autowired
    private AccountOperationModeService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ModelAndView getUserList() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> allUsers = userService.getUsers();
        modelAndView.addObject("users", allUsers);
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        if (userService.isTestMode()) {
            return "testModeWarn";
        }
        if (userService.getUserById(id).getAccount() != null) {
            long accountToBeDeletedId = userService.getUserById(id).getAccount().getId();
            accountService.deleteAccount(accountToBeDeletedId);
        }
        userService.deleteUser(id);
        return "redirect:/users/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String editUserForm(Model model, @RequestParam("id") int id) {
        if (userService.isTestMode()) {
            return "testModeWarn";
        }
        model.addAttribute("userAttribute", userService.getUserById(id));
        return "editUser";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editUser")
    public String updateUser(@ModelAttribute("userAttribute") User user) {
        if (userService.isTestMode()) {
            return "testModeWarn";
        }
        BankAccount bankAccount = null;
        if (userService.getUserById(user.getId()).getAccount() != null) {
            bankAccount = userService.getUserById(user.getId()).getAccount();
        }
        user.setAccount(bankAccount);
        userService.updateUserInfo(user);
        return "redirect:/users/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editAccount")
    public String editAccount(Model model, @RequestParam("id") int id) {
        if (accountService.isTestMode()) {
            return "testModeWarn";
        }
        if (userService.getUserById(id).getAccount() == null) {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setAmount(0);
            accountService.addAccount(bankAccount);
            userService.getUserById(id).setAccount(bankAccount);
        }
        return "redirect:/accounts/edit?id=" + userService.getUserById(id).getAccount().getId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addUserForm(Model model) {
        model.addAttribute("userAttribute", new User());
        return "addUser";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public String addUser(@ModelAttribute("userAttribute") User user) {
        userService.addUser(user);
        return "redirect:/users/list";
    }

}
