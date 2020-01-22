package com.hillel.mvc.bank.controller.user;

import com.hillel.mvc.bank.controller.dto.User;
import com.hillel.mvc.bank.controller.dto.UserUpdate;
import com.hillel.mvc.bank.models.dto.UserUpdateDTO;
import com.hillel.mvc.bank.utils.Mapper;
import com.hillel.mvc.bank.controller.dto.UserCreate;
import com.hillel.mvc.bank.models.dto.UserCreateDTO;
import com.hillel.mvc.bank.models.entities.UserEntity;
import com.hillel.mvc.bank.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bank")
@Slf4j
public class UsersController {

    private UserService userService;
    private Mapper mapper;

    @Autowired
    public UsersController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ModelAndView getUsers() {
        List<User> users = mapper.mapList(userService.getAllUsers(), User.class);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String getAddUserForm(Model model) {
        model.addAttribute("userAttribute", new UserEntity());
        return "addUser";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public ModelAndView addUser(@ModelAttribute("userAttribute") @Valid UserCreate createUser) {
        ModelAndView modelAndView = new ModelAndView();
        userService.addUser(mapper.map(createUser, UserCreateDTO.class));
        modelAndView.setViewName("redirect:/bank/users");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public ModelAndView getEditUserForm(@RequestParam("id") int id, Model model) {
        User user = mapper.map(userService.getUser(id), User.class);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("editUser");
        model.addAttribute("userAttribute", new UserEntity());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editUser/{id}")
    public ModelAndView getEditUserForm(@PathVariable("id") long id, @Valid @ModelAttribute("userAttribute") UserUpdate user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.updateUser(id , mapper.map(user, UserUpdateDTO.class));
        modelAndView.setViewName("redirect:/bank/users");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public ModelAndView deleteUser(@RequestParam("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        userService.deleteUser(id);
        modelAndView.setViewName("redirect:/bank/users");
        return modelAndView;
    }
}
