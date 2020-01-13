package com.hillel.bankserviceboot.controller;


import com.hillel.bankserviceboot.model.*;
import com.hillel.bankserviceboot.service.AccountService;
import com.hillel.bankserviceboot.service.BankService;
import com.hillel.bankserviceboot.service.CardService;
import com.hillel.bankserviceboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    @Qualifier("bankServiceImpl")
    private BankService bankService;

    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ModelAndView getAccountsList(HttpServletRequest request) {

        String username = request.getUserPrincipal().getName();
        UserEntity byUsername = userService.findByUsername(username);
        int usernameId = byUsername.getUserId();

        List<AccountEntity> accountsList = accountService.getAccounts()
                .stream().filter(accountEntity -> accountEntity.getUser().getUserId() == usernameId).collect(Collectors.toList());

        Set<RoleEntity> roles = byUsername.getRoles();
        for (RoleEntity role : roles) {
            if (role.getName().equals("ROLE_ADMIN") || role.getName().equals("ROLE_MANAGER")) {
                accountsList = accountService.getAccounts();
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts", accountsList);
        modelAndView.setViewName("accountsList");
        modelAndView.addObject("transfer", bankService.isTransferSupported());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deposit")
    public String depositAccountForm(Model model, @RequestParam("accountId") int accountId) {
        BalanceOperation balanceOperation = new BalanceOperation();
        balanceOperation.setAccountId(accountId);
        model.addAttribute("deposit", balanceOperation);
        return "accountDeposit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/depositAccount")
    public String depositAccount(@ModelAttribute("deposit") BalanceOperation balanceOperation) {
        bankService.deposit(balanceOperation.getAccountId(), balanceOperation.getAmount());
        return "redirect:/accounts/list";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/withdraw")
    public String withdrawAccountForm(Model model, @RequestParam("accountId") int accountId) {
        BalanceOperation balanceOperation = new BalanceOperation();
        balanceOperation.setAccountId(accountId);
        model.addAttribute("withdraw", balanceOperation);
        return "accountWithdraw";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/withdrawAccount")
    public String withdrawAccount(@ModelAttribute("withdraw") BalanceOperation balanceOperation) {
        bankService.withdraw(balanceOperation.getAccountId(), balanceOperation.getAmount());
        return "redirect:/accounts/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteAccount")
    public String deleteAccount(@RequestParam("accountId") int accountId) {
        accountService.deleteAccount(accountId);
        return "redirect:/accounts/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addCardForm")
    public String addCardForm(Model model) {

        Map<String, String> accountsMap = accountService.getAccountsMap();

        model.addAttribute("accountsMap", accountsMap);
        model.addAttribute("bankCard", new BankCard());
        return "cardAdd";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addCard")
    public String addCard(@ModelAttribute("bankCard") BankCard bankCard) {
        cardService.save(bankCard);
        return "redirect:/accounts/list";
    }


}
