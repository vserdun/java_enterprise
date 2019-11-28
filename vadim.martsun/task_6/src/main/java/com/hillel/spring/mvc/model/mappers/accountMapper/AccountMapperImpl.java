package com.hillel.spring.mvc.model.mappers.accountMapper;

import com.hillel.spring.mvc.dao.userRepository.UserRepository;
import com.hillel.spring.mvc.model.Account;
import com.hillel.spring.mvc.model.User;
import com.hillel.spring.mvc.model.requests.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AccountMapperImpl implements AccountMapper {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    UserRepository userRepository;

    @Override
    public Account getAccount(AccountRequest request){
        LocalDate creationDate = LocalDate.parse(request.getCreationDate(), dateTimeFormatter);
        User user = userRepository.getUserById(request.getUserId());
        if(user == null) return null;
        return new Account(0,request.getAmount(), request.getUserId(), user, creationDate);
    }
}
