package ru.lebedev.servicecarsharingusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.servicecarsharingusers.model.User;
import ru.lebedev.servicecarsharingusers.request.UserRequest;
import ru.lebedev.servicecarsharingusers.response.UserResponse;
import ru.lebedev.servicecarsharingusers.service.UserServiceImpl;
import ru.lebedev.servicecarsharingusers.mapper.UserMapper;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserMapper userMapper;

    @Autowired
    private UserController(UserServiceImpl userServiceImpl, UserMapper userMapper) {
        this.userServiceImpl = userServiceImpl;
        this.userMapper = userMapper;
    }

    @PostMapping
    private ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {
        User user = userMapper.mapToUser(userRequest);
        userServiceImpl.create(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateUser(
            @PathVariable Integer id,
            @RequestBody UserRequest userRequest) {
        UserResponse response = userServiceImpl.update(userRequest, id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userServiceImpl.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getUser(@PathVariable Integer id) {
        UserResponse response = userServiceImpl.get(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
