package ru.lebedev.servicecarsharingusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.servicecarsharingusers.exception.DeleteUserException;
import ru.lebedev.servicecarsharingusers.exception.UpdateUserException;
import ru.lebedev.servicecarsharingusers.exception.UserNotFoundException;
import ru.lebedev.servicecarsharingusers.exception.UserStatusException;
import ru.lebedev.servicecarsharingusers.request.AuthenticationRequest;
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

    @GetMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AuthenticationRequest authenticationRequest) throws UserNotFoundException, UserStatusException {
        boolean isAuth = userService.auth(authenticationRequest);

        return new ResponseEntity<>(isAuth, HttpStatus.OK);
    }

    @PostMapping("/create")
    private ResponseEntity<?> create(@RequestBody @Valid UserRequest userRequest) {
        UserResponse response = userService.create(userRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> read(@PathVariable Integer id) throws UserNotFoundException {
        UserResponse response = userService.get(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    private ResponseEntity<?> update(@PathVariable Integer id,
                                     @RequestBody @Valid UserRequest userRequest) throws UserNotFoundException, UpdateUserException {
        UserResponse response = userService.update(userRequest, id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    private ResponseEntity<?> delete(@PathVariable Integer id) throws UserNotFoundException, DeleteUserException {
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/exist")
    private ResponseEntity<?> isExist(@PathVariable Integer id) {
        boolean isExist = userService.isExist(id);
        return new ResponseEntity<>(isExist, HttpStatus.OK);
    }

    @PutMapping("/{id}/drive/start")
    public ResponseEntity<?> inDrive(@PathVariable Integer id) throws UserNotFoundException, UserStatusException {
        UserResponse response = userService.setStatusInDrive(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/drive/over")
    public ResponseEntity<?> notInDrive(@PathVariable Integer id) throws UserNotFoundException, UserStatusException {
        UserResponse response = userService.setStatusNotInDrive(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
