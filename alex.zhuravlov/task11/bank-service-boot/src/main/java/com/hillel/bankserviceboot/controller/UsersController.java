package com.hillel.bankserviceboot.controller;


import com.hillel.bankserviceboot.model.AccountEntity;
import com.hillel.bankserviceboot.model.UserEntity;
import com.hillel.bankserviceboot.service.AccountService;
import com.hillel.bankserviceboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addUserForm(Model model) {
        model.addAttribute("userAttribute", new UserEntity());
        return "userAdd";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public String addUser(@ModelAttribute("userAttribute") @Validated UserEntity userEntity) {
        userService.addUser(userEntity);
        return "redirect:/users/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ModelAndView getUsersList() {
        List<UserEntity> usersList = userService.getUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", usersList);
        modelAndView.setViewName("usersList");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String editUserForm(Model model, @RequestParam("userId") int userId) {
        model.addAttribute("userAttribute", userService.getUser(userId));
        return "userEdit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editUser")
    public String updateUser(@ModelAttribute("userAttribute") @Validated UserEntity userEntity) {
        userService.addUser(userEntity);
        return "redirect:/users/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteUser")
    public String deleteUser(@RequestParam("userId") int userId) {
        userService.deleteUser(userId);
        List<AccountEntity> accounts = accountService.getAccounts();
        for (AccountEntity accountEntity : accounts
        ) {
            if (accountEntity.getUser().getUserId() == userId) {
                accountService.deleteAccount(accountEntity.getAccountId());
            }
        }
        return "redirect:/users/list";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/addAccount")
    public String addAccountForm(Model model, @RequestParam("userId") int userId) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUser(userService.getUser(userId));
        accountService.addAccount(accountEntity);
        return "redirect:/accounts/list";
    }


}
