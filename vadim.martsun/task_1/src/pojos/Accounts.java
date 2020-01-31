package pojos;

import java.util.Collection;

public class Accounts {
    private Collection<BankAccount> accountsA;
    private Collection<BankAccount> accountsB;

    public Accounts(){}

    public Collection<BankAccount> getAccountsA() {
        return accountsA;
    }

    public void setAccountsA(Collection<BankAccount> accountsA) {
        this.accountsA = accountsA;
    }

    public Collection<BankAccount> getAccountsB() {
        return accountsB;
    }

    public void setAccountsB(Collection<BankAccount> accountsB) {
        this.accountsB = accountsB;
    }
}
