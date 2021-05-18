package ru.lebedev.servicecarsharingusers.service;

import ru.lebedev.servicecarsharingusers.exception.UserNotFoundException;
import ru.lebedev.servicecarsharingusers.exception.UserStatusException;
import ru.lebedev.servicecarsharingusers.request.AuthenticationRequest;
import ru.lebedev.servicecarsharingusers.request.UserRequest;
import ru.lebedev.servicecarsharingusers.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAll();

    boolean auth(AuthenticationRequest authenticationRequest) throws UserNotFoundException;

    UserResponse create(UserRequest userRequest);

    UserResponse get(int id) throws UserNotFoundException;

    UserResponse update(UserRequest userRequest, int id) throws UserNotFoundException;

    void delete(int id) throws UserNotFoundException;

    boolean isExist(int id);

    UserResponse setStatusInDrive(int id) throws UserNotFoundException, UserStatusException;

    UserResponse setStatusNotInDrive(int id) throws UserNotFoundException, UserStatusException;
}
