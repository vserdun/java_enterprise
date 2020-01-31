package com.hillel.bankserviceboot.service;


import com.hillel.bankserviceboot.dao.AccountRepository;
import com.hillel.bankserviceboot.model.AccountEntity;
import com.hillel.bankserviceboot.model.RoleEntity;
import com.hillel.bankserviceboot.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void addAccount(AccountEntity accountEntity) {
        accountRepository.save(accountEntity);
    }

    @Override
    public List<AccountEntity> getAccounts() {
        return accountRepository.getAccountsList();
    }

    @Override
    public AccountEntity getAccount(int id) {
        return accountRepository.getAccountEntityById(id);
    }

    @Override
    public void updateAccount(AccountEntity accountEntity) {
        accountRepository.save(accountEntity);
    }

    @Override
    public void deleteAccount(int id) {
        accountRepository.delete(id);
    }

    @Override
    public Map<String, String> getAccountsMap() {
        List<AccountEntity> accounts = getAccounts();

        Map<String, String> accountMap = new HashMap<>();

        for (AccountEntity account : accounts) {
            String id = String.valueOf(account.getAccountId());
            String text = "Account id:" + id;
            accountMap.put(id, text);
        }
        return accountMap;
    }

    @Override
    public List<AccountEntity> getUserAccounts(UserEntity user) {
        List<AccountEntity> accountsList = getAccounts()
                .stream().filter(accountEntity -> accountEntity.getUser().getUserId() == user.getUserId()).collect(Collectors.toList());

        Set<RoleEntity> roles = user.getRoles();
        for (RoleEntity role : roles) {
            if (role.getName().equals("ROLE_ADMIN") || role.getName().equals("ROLE_MANAGER")) {
                accountsList = getAccounts();
            }
        }
        return accountsList;
    }


}
