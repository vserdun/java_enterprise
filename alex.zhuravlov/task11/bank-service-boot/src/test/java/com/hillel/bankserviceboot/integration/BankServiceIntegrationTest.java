package com.hillel.bankserviceboot.integration;

import com.hillel.bankserviceboot.model.AccountEntity;
import com.hillel.bankserviceboot.model.UserEntity;
import com.hillel.bankserviceboot.service.AccountService;
import com.hillel.bankserviceboot.service.BankService;
import com.hillel.bankserviceboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankServiceIntegrationTest {

    @Autowired
    @Qualifier("bankServiceImpl")
    BankService bankService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Test
    public void addTest() {
        UserEntity user1 = new UserEntity("name1", "lastname1");
        UserEntity user2 = new UserEntity("name2", "lastname2");
        userService.addUser(user1);
        userService.addUser(user2);

        accountService.addAccount(new AccountEntity(1000, user1));
        accountService.addAccount(new AccountEntity(1000, user2));

        List<UserEntity> users = userService.getUsers();
        assertNotNull("Users should not be null", users);
        assertEquals("Wrong count", 2, users.size());

        List<AccountEntity> accounts = accountService.getAccounts();
        assertNotNull("Accounts should not be null", accounts);
        assertEquals("Wrong count", 2, accounts.size());

    }

    @Test
    public void withdrawTest() {
        AccountEntity accountBeforeTest = accountService.getAccount(1);
        int amount = 222;


        log.info("account balance before test {}", accountBeforeTest.getAccountBalance());
        boolean isWithdraw = bankService.withdraw(1, amount);
        AccountEntity accountAfterTest = accountService.getAccount(1);
        assertEquals(accountBeforeTest.getAccountBalance() - amount, accountAfterTest.getAccountBalance(), 0.0001);
        log.info("account balance after test {}", accountAfterTest.getAccountBalance());
        assertTrue(isWithdraw);
    }

    @Test
    public void depositTest() {
        AccountEntity accountBeforeTest = accountService.getAccount(1);
        int amount = 111;

        log.info("account balance before test {}", accountBeforeTest.getAccountBalance());
        boolean isDeposit = bankService.deposit(1, amount);
        AccountEntity accountAfterTest = accountService.getAccount(1);
        assertEquals(accountBeforeTest.getAccountBalance() + amount, accountAfterTest.getAccountBalance(), 0.0001);
        log.info("account balance after test {}", accountAfterTest.getAccountBalance());
        assertTrue(isDeposit);
    }

    @Test
    public void transferTest() {
        AccountEntity ac1BeforeTest = accountService.getAccount(1);
        AccountEntity ac2BeforeTest = accountService.getAccount(2);

        int amount = 200;


        log.info("balances before transfer ac1 {}, ac2 {}", ac1BeforeTest.getAccountBalance(), ac2BeforeTest.getAccountBalance());
        boolean isTransfered = bankService.transfer(1, 2, amount);

        AccountEntity ac1AfterTest = accountService.getAccount(1);
        AccountEntity ac2AfterTest = accountService.getAccount(2);


        assertEquals(ac1BeforeTest.getAccountBalance() - amount, ac1AfterTest.getAccountBalance(), 0.001);
        assertEquals(ac2BeforeTest.getAccountBalance() + amount, ac2AfterTest.getAccountBalance(), 0.001);
        assertTrue(isTransfered);
        log.info("balances after transfer ac1 {}, ac2 {}", ac2AfterTest.getAccountBalance(), ac2AfterTest.getAccountBalance());

    }
}
