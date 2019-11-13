package com.hillel.mapper;

import com.hillel.model.BankAcc;
import com.hillel.model.Database;
import com.hillel.model.User;
import com.hillel.model.requests.BankAccRequest;
import com.hillel.model.requests.UserRequest;
import lombok.extern.slf4j.Slf4j;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class MapperImpl implements Mapper {
    private Database database = Database.getInstance();

    @Override
    public BankAcc mapBankAccount(BankAccRequest request) {
        User user = database.getUsers().get(request.getUserId());
        return new BankAcc(request.getBalance(), user);
    }

    @Override
    public User mapUser(UserRequest request) {
        try {
            Date userBirthDate = new SimpleDateFormat("dd.MM.yyyy").parse(request.getUserBirthDate());
            User user = new User();
            user.setFirstName(request.getUserFirstName());
            user.setLastName(request.getUserLastName());
            user.setBirthDate(userBirthDate);
            return user;
        }catch (ParseException e){
            log.info("Could not parse date");
            return null;
        }
    }
}
