package ru.lebedev.servicecarsharingusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.servicecarsharingusers.dto.UserDto;
import ru.lebedev.servicecarsharingusers.model.User;
import ru.lebedev.servicecarsharingusers.service.UserService;
import ru.lebedev.servicecarsharingusers.utils.MappingUtils;

@RestController
public class UserController {

    private final UserService userService;
    private final MappingUtils mappingUtils;

    @Autowired
    private UserController(UserService userService, MappingUtils mappingUtils) {
        this.userService = userService;
        this.mappingUtils = mappingUtils;
    }

    @PostMapping("/api/users")
    private ResponseEntity addUser(@RequestBody UserDto userDto) {
        User user = mappingUtils.mapToUser(userDto);
        userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/api/users")
    private ResponseEntity updateUser(@RequestBody UserDto userDto) {
        User user = mappingUtils.mapToUser(userDto);
        userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/api/users")
    private ResponseEntity deleteUser(@RequestBody UserDto userDto) {
        User user = mappingUtils.mapToUser(userDto);
        userService.delete(user);

        return new ResponseEntity<>("User deleted!", HttpStatus.OK);
    }

    @GetMapping("/api/users")
    private User getUser(@RequestBody UserDto userDto) {
        User user = mappingUtils.mapToUser(userDto);

        return userService.get(user.getId());
    }
}
