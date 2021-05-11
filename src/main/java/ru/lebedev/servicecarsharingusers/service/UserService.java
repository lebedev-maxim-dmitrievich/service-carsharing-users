package ru.lebedev.servicecarsharingusers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lebedev.servicecarsharingusers.model.User;
import ru.lebedev.servicecarsharingusers.repository.UserDao;
import ru.lebedev.servicecarsharingusers.service.impl.UserServiceImpl;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService implements UserServiceImpl {

    private final UserDao userRepository;

    @Autowired
    public UserService(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
