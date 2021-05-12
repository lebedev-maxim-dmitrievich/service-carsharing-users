package ru.lebedev.servicecarsharingusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.servicecarsharingusers.dto.UserDto;
import ru.lebedev.servicecarsharingusers.model.User;
import ru.lebedev.servicecarsharingusers.service.UserService;
import ru.lebedev.servicecarsharingusers.mapper.UserMapper;

@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    private UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/api/users")
    private ResponseEntity addUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/api/users")
    private ResponseEntity updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/api/users")
    private ResponseEntity deleteUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.delete(user);

        return new ResponseEntity<>("User deleted!", HttpStatus.OK);
    }

    @GetMapping("/api/users")
    private User getUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);

        return userService.get(user.getId());
    }
}
