package ru.lebedev.servicecarsharingusers.mapper;

import org.springframework.stereotype.Service;
import ru.lebedev.servicecarsharingusers.model.User;
import ru.lebedev.servicecarsharingusers.request.UserRequest;
import ru.lebedev.servicecarsharingusers.response.UserResponse;

@Service
public class UserMapper {

    public UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setPatronymic(user.getPatronymic());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setAge(user.getAge());
        userResponse.setPassportID(user.getPassportID());
        userResponse.setDrivingLicenceID(user.getDrivingLicenceID());

        return userResponse;
    }

    public User mapToUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPatronymic(userRequest.getPatronymic());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setAge(userRequest.getAge());
        user.setPassportID(userRequest.getPassportID());
        user.setDrivingLicenceID(userRequest.getDrivingLicenceID());
        user.setPhoto(userRequest.getPhoto());

        return user;
    }

    public User mergeIntoUser(UserRequest userRequest, User user) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPatronymic(userRequest.getPatronymic());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setAge(userRequest.getAge());
        user.setPassportID(userRequest.getPassportID());
        user.setDrivingLicenceID(userRequest.getDrivingLicenceID());
        user.setPhoto(userRequest.getPhoto());

        return user;
    }
}
