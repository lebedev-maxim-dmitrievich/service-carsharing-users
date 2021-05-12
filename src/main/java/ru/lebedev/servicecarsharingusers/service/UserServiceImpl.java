package ru.lebedev.servicecarsharingusers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lebedev.servicecarsharingusers.mapper.UserMapper;
import ru.lebedev.servicecarsharingusers.model.User;
import ru.lebedev.servicecarsharingusers.repository.UserRepository;
import ru.lebedev.servicecarsharingusers.request.UserRequest;
import ru.lebedev.servicecarsharingusers.response.UserResponse;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements ru.lebedev.servicecarsharingusers.service.impl.UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse get(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException();
        }
        UserResponse response = userMapper.mapToUserResponse(user.get());

        return response;
    }

    @Override
    public UserResponse create(User user) {
        userRepository.save(user);
        UserResponse response = userMapper.mapToUserResponse(user);

        return response;
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse update(UserRequest userRequest, int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user = userOptional.get();
        userRepository.save(userMapper.mergeIntoUser(userRequest, user));
        UserResponse response = userMapper.mapToUserResponse(user);

        return response;
    }
}
