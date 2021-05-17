package ru.lebedev.servicecarsharingusers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lebedev.servicecarsharingusers.exception.UserNotFoundException;
import ru.lebedev.servicecarsharingusers.exception.UserStatusException;
import ru.lebedev.servicecarsharingusers.mapper.UserMapper;
import ru.lebedev.servicecarsharingusers.model.User;
import ru.lebedev.servicecarsharingusers.model.enums.UserStatus;
import ru.lebedev.servicecarsharingusers.repository.UserRepository;
import ru.lebedev.servicecarsharingusers.request.UserRequest;
import ru.lebedev.servicecarsharingusers.response.UserResponse;
import ru.lebedev.servicecarsharingusers.service.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public List<UserResponse> getAll() {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userResponses.add(userMapper.mapToUserResponse(user));
        }
        return userResponses;
    }

    @Override
    @Transactional
    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.mapToUser(userRequest);
        user.setStatus(UserStatus.NOT_IN_DRIVE);
        userRepository.save(user);
        UserResponse response = userMapper.mapToUserResponse(user);

        return response;
    }

    @Override
    @Transactional
    public UserResponse get(int id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        UserResponse response = userMapper.mapToUserResponse(userOptional.get());

        return response;
    }

    @Override
    @Transactional
    public UserResponse update(UserRequest userRequest, int id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        User user = userOptional.get();
        userRepository.save(userMapper.mergeIntoUser(userRequest, user));
        UserResponse response = userMapper.mapToUserResponse(user);

        return response;
    }

    @Override
    @Transactional
    public void delete(int id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public boolean isExist(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public UserResponse setStatusInDrive(int id) throws UserNotFoundException, UserStatusException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        User user = userOptional.get();
        if (user.getStatus().equals(UserStatus.IN_DRIVE)) {
            throw new UserStatusException("the user is already driving");
        }
        user.setStatus(UserStatus.IN_DRIVE);
        userRepository.save(user);
        UserResponse response = userMapper.mapToUserResponse(user);

        return response;
    }

    @Override
    @Transactional
    public UserResponse setStatusNotInDrive(int id) throws UserNotFoundException, UserStatusException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        User user = userOptional.get();
        if (user.getStatus().equals(UserStatus.NOT_IN_DRIVE)) {
            throw new UserStatusException("the user is not driving now");
        }
        user.setStatus(UserStatus.NOT_IN_DRIVE);
        userRepository.save(user);
        UserResponse response = userMapper.mapToUserResponse(user);

        return response;
    }
}
