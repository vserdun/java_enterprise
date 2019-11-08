import objects.Account;
import random.RandomBankAccountService;
import random.RandomBankAccountServiceImpl;
import service.BankService;
import service.BankServiceImpl;

public class Main {

    public static void main(String ... args){
        System.out.println("Hello");
        RandomBankAccountService randomBankAccountService = new RandomBankAccountServiceImpl();
        Account account = randomBankAccountService.getRandomAccounts(1).get(0);

        BankService bankService = new BankServiceImpl();
        bankService.topUp(account, 105.0f);
    }
}
