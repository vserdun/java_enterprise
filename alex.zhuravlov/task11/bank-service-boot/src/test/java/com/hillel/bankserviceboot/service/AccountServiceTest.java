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
        accountService.addAccount(new AccountEntity(100, new UserEntity()));
        accountService.addAccount(new AccountEntity(200, new UserEntity()));
        accountService.addAccount(new AccountEntity(300, new UserEntity()));
        verify(accountRepository, times(3)).save(any(AccountEntity.class));
    }

    @Test
    public void getAccounts() {
        when(accountRepository.getAccountsList()).thenReturn(Arrays.asList(new AccountEntity(100, new UserEntity()), new AccountEntity(300, new UserEntity())));
        List<AccountEntity> accounts = accountService.getAccounts();
        assertNotNull("Account list must be not null", accounts);
        assertEquals("Wrong accounts count", 2, accounts.size());
    }

    @Test
    public void getAccount() {
        ArgumentCaptor<Integer> accIdArgCaptor = ArgumentCaptor.forClass(Integer.class);
        when(accountRepository.getAccountEntityById(accIdArgCaptor.capture())).thenReturn(new AccountEntity(100, new UserEntity()));

        AccountEntity account = accountService.getAccount(15);

        assertNotNull("Account must be not null", account);
        assertEquals("Wrong balance value", 100, account.getAccountBalance(), 0.0001);

    }

    @Test
    public void updateAccount() {
        AccountEntity accountEntity = new AccountEntity(100, new UserEntity());
        accountService.updateAccount(accountEntity);
        verify(accountRepository, times(1)).save(accountEntity);
    }

    @Test
    public void deleteAccount() {
        accountService.deleteAccount(1);
        verify(accountRepository, times(1)).delete(1);
    }

    @Test
    public void getAccountsMap() {
        AccountEntity acc1 = new AccountEntity(100, new UserEntity());
        acc1.setAccountId(1);
        AccountEntity acc2 = new AccountEntity(300, new UserEntity());
        acc2.setAccountId(2);
        when(accountRepository.getAccountsList()).thenReturn(Arrays.asList(acc1, acc2));

        Map<String, String> accountsMap = accountService.getAccountsMap();

        assertNotNull("Account map mus be not null", accountsMap);
        assertEquals("Wrong map size", 2, accountsMap.size());
    }


}