package com.hillel.mvc.springboot.controllers;
import com.hillel.mvc.springboot.dao.accountRepository.AccountRepository;
import com.hillel.mvc.springboot.model.Account;
import com.hillel.mvc.springboot.model.mappers.accountMapper.AccountMapper;
import com.hillel.mvc.springboot.model.requests.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountPageController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/accountList")
    public ModelAndView getUsersList() {
        List<Account> accountList = accountRepository.getAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("accounts", accountList);
        modelAndView.setViewName("accountsPage");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addAccountForm(Model model) {
        model.addAttribute("accountAttribute", new AccountRequest());
        return "addAccount";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addAccount")
    public String addAccount(@ModelAttribute("accountAttribute") @Valid AccountRequest accountRequest,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addAccount";
        }
        Account account = accountMapper.getAccount(accountRequest);
        accountRepository.save(account);
        return "redirect:/accounts/accountList";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{accountId}")
    public String editAccountPage(@PathVariable("accountId") int accountId, Model model) {
        Account account = accountRepository.getById(accountId);
        AccountRequest accountRequest = new AccountRequest(accountId,
                account.getAmount(),
                account.getCreationDate().toString(),
                account.getUserId());
        model.addAttribute("accountAttribute", accountRequest);
        return "editAccount";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit/update")
    public String editAccount(@ModelAttribute("accountAttribute") @Valid AccountRequest accountRequest,
                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "editAccount";
        }
        accountRepository.update(accountRequest.getId(), accountRequest);
        return "redirect:/accounts/accountList";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{accountId}")
    public String deleteAccount(@PathVariable("accountId") int accountId) {
        accountRepository.delete(accountId);

        return "redirect:/accounts/accountList";
    }
}
