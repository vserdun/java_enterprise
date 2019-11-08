package random;

import objects.Account;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RandomBankAccountService {
    List<Account> getRandomAccounts(int count);
}
