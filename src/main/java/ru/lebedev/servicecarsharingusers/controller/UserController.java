package ru.lebedev.servicecarsharingusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.servicecarsharingusers.exception.InvalidateDataUserException;
import ru.lebedev.servicecarsharingusers.exception.UserNotFoundException;
import ru.lebedev.servicecarsharingusers.request.UserRequest;
import ru.lebedev.servicecarsharingusers.response.UserResponse;
import ru.lebedev.servicecarsharingusers.service.UserService;
import ru.lebedev.servicecarsharingusers.service.impl.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    private ResponseEntity<?> getAll() {
        List<UserResponse> response = userService.getAll();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody @Valid UserRequest userRequest) {
        UserResponse response = userService.create(userRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> read(@PathVariable Integer id) throws UserNotFoundException {
        UserResponse response = userService.get(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable Integer id,
                                     @RequestBody @Valid UserRequest userRequest) throws UserNotFoundException {
        UserResponse response = userService.update(userRequest, id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Integer id) throws UserNotFoundException {
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/exist")
    private ResponseEntity<?> isExist(@PathVariable Integer id) {
        boolean isExist = userService.isExist(id);
        return new ResponseEntity<>(isExist, HttpStatus.OK);
    }
}
