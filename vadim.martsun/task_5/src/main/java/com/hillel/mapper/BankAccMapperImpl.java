package com.hillel.mapper;

import com.hillel.model.BankAcc;
import com.hillel.model.User;
import com.hillel.model.requests.BankAccRequest;
import lombok.extern.slf4j.Slf4j;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class BankAccMapperImpl implements BankAccMapper {
    @Override
    public BankAcc map(BankAccRequest request) {
        try {
            Date userBirthDate = new SimpleDateFormat("dd.MM.yyyy").parse(request.getUserBirthDate());
            User user = new User();
            user.setFirstName(request.getUserFirstName());
            user.setLastName(request.getUserLastName());
            user.setBirthDate(userBirthDate);
            return new BankAcc(request.getBalance(), user);
        } catch (ParseException e) {
            log.info("Unable to parse date");
            return null;
        }
    }
}
