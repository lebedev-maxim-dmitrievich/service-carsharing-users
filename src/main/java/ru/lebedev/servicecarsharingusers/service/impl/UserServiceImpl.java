package ru.lebedev.servicecarsharingusers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lebedev.servicecarsharingusers.exception.UserNotFoundException;
import ru.lebedev.servicecarsharingusers.exception.UserStatusException;
import ru.lebedev.servicecarsharingusers.mapper.UserMapper;
import ru.lebedev.servicecarsharingusers.model.User;
import ru.lebedev.servicecarsharingusers.model.enums.UserStatus;
import ru.lebedev.servicecarsharingusers.repository.UserRepository;
import ru.lebedev.servicecarsharingusers.request.AuthenticationRequest;
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

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
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
    public boolean auth(AuthenticationRequest authenticationRequest) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(authenticationRequest.getEmail());
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        User user = userOptional.get();
        if (!checkPassword(user.getPassword(), authenticationRequest.getPassword())) {
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.mapToUser(userRequest);
        user.setStatus(UserStatus.NOT_IN_DRIVE);
        String password = user.getPassword();
        System.out.println(password);
        user.setPassword(hashPassword(password));
        System.out.println(user.getPassword());
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
        user = userMapper.mergeIntoUser(userRequest, user);
        String password = user.getPassword();
        user.setPassword(hashPassword(password));
        userRepository.save(user);
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

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private boolean checkPassword(String hashPassword, String password) {
        return passwordEncoder.matches(password, hashPassword);
    }
}
