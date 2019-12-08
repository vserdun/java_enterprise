package com.hillel.spring.mvc.model.mappers.userMapper;

import com.hillel.spring.mvc.model.Gender;
import com.hillel.spring.mvc.model.User;
import com.hillel.spring.mvc.model.requests.UserRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UserMapperImpl implements UserMapper {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public User getUser(UserRequest userRequest) {
        LocalDate birthDate = LocalDate.parse(userRequest.getBirthDate(), dateTimeFormatter);
        Gender gender = Gender.fromString(userRequest.getGender());

        return new User(0, userRequest.getLastName(),
                userRequest.getFirstName(),
                birthDate,
                gender);
    }
}
