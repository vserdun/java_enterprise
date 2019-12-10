package com.hillel.mvc.springboot.controllers;

import com.hillel.mvc.springboot.dao.userRepository.UserRepository;
import com.hillel.mvc.springboot.model.User;
import com.hillel.mvc.springboot.model.mappers.userMapper.UserMapper;
import com.hillel.mvc.springboot.model.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/usersList")
    public ModelAndView getUsersList() {
        List<User> userList = userRepository.getAllUsers();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("users", userList);
        modelAndView.setViewName("usersPage");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addUserForm(Model model) {
        model.addAttribute("userAttribute", new UserRequest());
        return "addUser";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public String addUser(@ModelAttribute("userAttribute") @Valid UserRequest userRequest,
                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "addUser";
        }
        User user = userMapper.getUser(userRequest);
        userRepository.save(user);
        return "redirect:/users/usersList";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {
        userRepository.delete(userId);

        return "redirect:/users/usersList";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{userId}")
    public String editUserPage(@PathVariable("userId") int userId, Model model) {
        User user = userRepository.getUserById(userId);
        UserRequest userRequest = new UserRequest(user.getId(),
                                                  user.getLastName(),
                                                  user.getFirstName(),
                                                  user.getBirthDate().toString(),
                                                  user.getGender().toString());
        model.addAttribute("userAttribute", userRequest);
        return "editUser";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit/update")
    public String editUser(@ModelAttribute("userAttribute") @Valid UserRequest user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "editUser";
        }
        userRepository.update(user.getId(), user);
        return "redirect:/users/usersList";
    }
}
