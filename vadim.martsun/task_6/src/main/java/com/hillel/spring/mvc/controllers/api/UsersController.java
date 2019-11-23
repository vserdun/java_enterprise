package com.hillel.spring.mvc.controllers.api;

import com.hillel.spring.mvc.dao.userRepository.UserRepository;
import com.hillel.spring.mvc.model.User;
import com.hillel.spring.mvc.model.mappers.userMapper.UserMapper;
import com.hillel.spring.mvc.model.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> userList = userRepository.getAllUsers();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
        User user = userRepository.getUserById(Integer.parseInt(userId));
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody UserRequest userRequest){
        User user = userMapper.getUser(userRequest);
        userRepository.save(user);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable("userId") String userId, @RequestBody UserRequest userRequest){
        User updatedUser = userMapper.getUser(userRequest);
        if(userRequest == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        boolean successfully = userRepository.update(Integer.parseInt(userId), updatedUser);

        return new ResponseEntity((successfully) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") String userId){
        boolean successfully = userRepository.delete(Integer.parseInt(userId));

        return new ResponseEntity((successfully) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
