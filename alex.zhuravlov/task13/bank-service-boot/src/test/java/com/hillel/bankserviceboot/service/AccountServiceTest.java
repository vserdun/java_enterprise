package com.hillel.bankserviceboot.service;

import com.hillel.bankserviceboot.dao.AccountRepository;
import com.hillel.bankserviceboot.model.AccountEntity;
import com.hillel.bankserviceboot.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;


    @Test
    public void addAccount() {
        AccountEntity acc1 = new AccountEntity(new UserEntity());
        acc1.setAccountBalance(100);
        AccountEntity acc2 = new AccountEntity(new UserEntity());
        acc1.setAccountBalance(200);

        accountService.addAccount(acc1);
        accountService.addAccount(acc2);
        verify(accountRepository, times(2)).save(any(AccountEntity.class));
    }

    @Test
    public void getAccounts() {
        when(accountRepository.findAll()).thenReturn(Arrays.asList(new AccountEntity(new UserEntity()), new AccountEntity(new UserEntity())));
        List<AccountEntity> accounts = accountService.getAccounts();
        assertNotNull("Account list must be not null", accounts);
        assertEquals("Wrong accounts count", 2, accounts.size());
    }

    @Test
    public void getAccount() {
        ArgumentCaptor<Integer> accIdArgCaptor = ArgumentCaptor.forClass(Integer.class);
        AccountEntity accountEntity = new AccountEntity(new UserEntity());
        accountEntity.setAccountBalance(140);
        when(accountRepository.findById(accIdArgCaptor.capture())).thenReturn(java.util.Optional.of(accountEntity));

        AccountEntity account = accountService.getAccount(15);

        assertNotNull("Account must be not null", account);
        assertEquals("Wrong balance value", 140, account.getAccountBalance(), 0.0001);

    }

    @Test
    public void updateAccount() {
        AccountEntity accountEntity = new AccountEntity(new UserEntity());
        accountService.updateAccount(accountEntity);
        verify(accountRepository, times(1)).save(accountEntity);
    }

    @Test
    public void deleteAccount() {
        accountService.deleteAccount(1);
        verify(accountRepository, times(1)).deleteById(1);
    }

    @Test
    public void getAccountsMap() {
        AccountEntity acc1 = new AccountEntity(new UserEntity());
        acc1.setAccountId(1);
        acc1.setAccountBalance(100);
        AccountEntity acc2 = new AccountEntity(new UserEntity());
        acc2.setAccountId(2);
        acc2.setAccountBalance(200);
        when(accountRepository.findAll()).thenReturn(Arrays.asList(acc1, acc2));

        Map<String, String> accountsMap = accountService.getAccountsMap();

        assertNotNull("Account map mus be not null", accountsMap);
        assertEquals("Wrong map size", 2, accountsMap.size());
    }


}