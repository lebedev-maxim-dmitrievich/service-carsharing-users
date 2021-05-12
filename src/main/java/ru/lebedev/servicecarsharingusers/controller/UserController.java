package ru.lebedev.servicecarsharingusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.servicecarsharingusers.request.UserRequest;
import ru.lebedev.servicecarsharingusers.response.UserResponse;
import ru.lebedev.servicecarsharingusers.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    private UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    private ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {
        UserResponse response = userServiceImpl.create(userRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
