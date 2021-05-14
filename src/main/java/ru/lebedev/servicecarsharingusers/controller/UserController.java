package ru.lebedev.servicecarsharingusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.servicecarsharingusers.exception.InvalidateDataUserException;
import ru.lebedev.servicecarsharingusers.exception.UserNotFoundException;
import ru.lebedev.servicecarsharingusers.model.User;
import ru.lebedev.servicecarsharingusers.request.UserRequest;
import ru.lebedev.servicecarsharingusers.response.UserResponse;
import ru.lebedev.servicecarsharingusers.service.impl.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    private UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    private ResponseEntity<?> getAll() {
        List<UserResponse> response = userServiceImpl.getAll();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody @Valid UserRequest userRequest,
                                     Errors errors) throws InvalidateDataUserException {
        if (errors.hasErrors()) {
            throw new InvalidateDataUserException("incorrect data");
        }
        UserResponse response = userServiceImpl.create(userRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> read(@PathVariable Integer id) throws UserNotFoundException {
        UserResponse response = userServiceImpl.read(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable Integer id,
                                     @RequestBody @Valid UserRequest userRequest,
                                     Errors errors) throws InvalidateDataUserException, UserNotFoundException {
        if (errors.hasErrors()) {
            throw new InvalidateDataUserException("incorrect data");
        }
        UserResponse response = userServiceImpl.update(userRequest, id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Integer id) throws UserNotFoundException {
        userServiceImpl.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
