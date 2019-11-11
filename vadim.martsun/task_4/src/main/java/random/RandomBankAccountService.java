package random;

import objects.Account;
import java.util.List;

public interface RandomBankAccountService {
    List<Account> getRandomAccounts(int count);
}
