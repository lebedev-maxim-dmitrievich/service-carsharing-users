package ru.lebedev.servicecarsharingusers.service;

import ru.lebedev.servicecarsharingusers.exception.UserNotFoundException;
import ru.lebedev.servicecarsharingusers.model.User;
import ru.lebedev.servicecarsharingusers.request.UserRequest;
import ru.lebedev.servicecarsharingusers.response.UserResponse;

import java.util.List;

public interface UserService {
    List<User> getAll();

    UserResponse create(UserRequest userRequest);

    UserResponse read(int id) throws UserNotFoundException;

    UserResponse update(UserRequest userRequest, int id) throws UserNotFoundException;


    void delete(int id) throws UserNotFoundException;
}
