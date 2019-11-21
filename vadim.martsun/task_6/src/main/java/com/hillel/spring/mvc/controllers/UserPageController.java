package com.hillel.spring.mvc.controllers;

import com.hillel.spring.mvc.dao.userRepository.UserRepository;
import com.hillel.spring.mvc.model.Gender;
import com.hillel.spring.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserPageController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/usersList")
    public ModelAndView getUsersList(){
        User user = new User("Martsun", "Vadim", LocalDate.of(1997,4,6), Gender.MALE);
        userRepository.save(user);
        List<User> userList = userRepository.getAllUsers();

        ModelAndView modelAndView  = new ModelAndView();

        modelAndView.addObject("users", userList);
        modelAndView.setViewName("usersPage");
        return modelAndView;
    }
}
