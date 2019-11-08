package random;

import objects.Account;
import objects.Gender;
import objects.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RandomBankAccountServiceImpl implements RandomBankAccountService {
    private Random random = new Random();

    @Override
    public List<Account> getRandomAccounts(int count) {
        List<Account> accounts = new ArrayList<>();

        for(int i = 0; i < count; i++){
            accounts.add(getRandomAccount());
        }

        return accounts;
    }

    private Account getRandomAccount(){
        long accountId = Math.abs(random.nextLong());
        Float amount = Math.abs(random.nextInt(10000) + random.nextFloat());
        User user = getRandomUser();
        LocalDate creationDate = getRandomDate(2010, LocalDate.now().getYear());

        return new Account(accountId, amount, user, creationDate);
    }

    private User getRandomUser(){
        Names firstName = Names.values()[random.nextInt(Names.values().length)];
        String lastName = LastNames.values()[random.nextInt(LastNames.values().length)].getName();
        Gender gender = firstName.getGender();
        LocalDate birthDate = getRandomDate(1940, 2000);

        return new User(lastName, firstName.getName(), birthDate, gender);
    }

    private LocalDate getRandomDate(int minYear, int maxYear){
        int randomYear = random.nextInt(maxYear - minYear) + minYear;
        int randomMonth = random.nextInt(12) + 1;
        int randomDay = random.nextInt(28) + 1;

        return LocalDate.of(randomYear, randomMonth, randomDay);
    }
}
