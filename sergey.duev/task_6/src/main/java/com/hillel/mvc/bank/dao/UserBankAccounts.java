package com.hillel.mvc.bank.dao;

import java.util.List;

public interface UserBankAccounts {

    void addUserBankAccount(long userId, long bankAccountId);

    void deleteUserBankAccount(long userId, long bankAccountId);

    List<Long> getUserBankAccounts(long userId);

}
