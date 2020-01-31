package com.hillel.bankserviceboot.service;

import com.hillel.bankserviceboot.model.AccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class BankServiceImplTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private BankServiceImpl bankService;


    @Test
    public void withdrawTest() {
        AccountEntity ac = new AccountEntity();
        ac.setAccountId(1);
        ac.setAccountBalance(1000);
        when(accountService.getAccount(1)).thenReturn(ac);

        log.info("account balance before test {}", ac.getAccountBalance());
        boolean isWithdraw = bankService.withdraw(1, 100);
        assertEquals(900.0, ac.getAccountBalance(), 0.0001);
        log.info("account balance after test {}", ac.getAccountBalance());
        assertTrue(isWithdraw);
    }

    @Test
    public void depositTest() {
        AccountEntity ac = new AccountEntity();
        ac.setAccountId(1);
        ac.setAccountBalance(0);
        when(accountService.getAccount(1)).thenReturn(ac);

        log.info("account balance before test {}", ac.getAccountBalance());
        boolean isDeposit = bankService.deposit(1, 111);
        assertEquals(111.0, ac.getAccountBalance(), 0.0001);
        log.info("account balance after test {}", ac.getAccountBalance());
        assertTrue(isDeposit);
    }

    @Test
    public void transferTest() {
        AccountEntity ac1 = new AccountEntity();
        ac1.setAccountId(1);
        ac1.setAccountBalance(1000);

        AccountEntity ac2 = new AccountEntity();
        ac2.setAccountId(2);
        ac2.setAccountBalance(0);

        when(accountService.getAccount(1)).thenReturn(ac1);
        when(accountService.getAccount(2)).thenReturn(ac2);

        log.info("balances before transfer ac1 {}, ac2 {}", ac1.getAccountBalance(), ac2.getAccountBalance());
        boolean isTransfered = bankService.transfer(1, 2, 100.0);
        assertEquals(900, ac1.getAccountBalance(), 0.001);
        assertEquals(100, ac2.getAccountBalance(), 0.001);
        assertTrue(isTransfered);
        log.info("balances after transfer ac1 {}, ac2 {}", ac1.getAccountBalance(), ac2.getAccountBalance());


    }

}