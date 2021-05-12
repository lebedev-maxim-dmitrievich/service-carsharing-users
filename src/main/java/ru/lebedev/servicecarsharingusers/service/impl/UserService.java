package ru.lebedev.servicecarsharingusers.service.impl;

import ru.lebedev.servicecarsharingusers.model.User;
import ru.lebedev.servicecarsharingusers.request.UserRequest;
import ru.lebedev.servicecarsharingusers.response.UserResponse;

import java.util.List;

public interface UserService {
    List<User> getAll();
    UserResponse update(UserRequest userRequest, int id);
    UserResponse get(int id);
    UserResponse create(User user);
    void delete(int id);
}
