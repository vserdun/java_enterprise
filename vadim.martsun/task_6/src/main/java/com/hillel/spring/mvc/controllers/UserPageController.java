package com.hillel.spring.mvc.controllers;

import com.hillel.spring.mvc.dao.userRepository.UserRepository;
import com.hillel.spring.mvc.model.Gender;
import com.hillel.spring.mvc.model.User;
import com.hillel.spring.mvc.model.mappers.userMapper.UserMapper;
import com.hillel.spring.mvc.model.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/usersList")
    public ModelAndView getUsersList(){
        List<User> userList = userRepository.getAllUsers();

        ModelAndView modelAndView  = new ModelAndView();

        modelAndView.addObject("users", userList);
        modelAndView.setViewName("usersPage");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addUserForm(Model model) {
        model.addAttribute("userAttribute" , new UserRequest());
        return "addUser";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public String addUser(@ModelAttribute("userAttribute") UserRequest userRequest) {
        User user = userMapper.getUser(userRequest);
        userRepository.save(user);
        return "redirect:/users/usersList";
    }
}
