package ru.lebedev.servicecarsharingusers.service.impl;

import ru.lebedev.servicecarsharingusers.model.User;

import java.util.List;

public interface UserServiceImpl {
    List<User> getAll();
    User get(Integer id);
    User save(User user);
    void delete(User user);
}
