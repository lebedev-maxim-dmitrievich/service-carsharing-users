package ru.lebedev.servicecarsharingusers.mapper;

import org.springframework.stereotype.Service;
import ru.lebedev.servicecarsharingusers.dto.UserDto;
import ru.lebedev.servicecarsharingusers.model.User;

@Service
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPatronymic(user.getPatronymic());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAge(user.getAge());
        userDto.setPassportID(user.getPassportID());
        userDto.setDrivingLicenceID(user.getDrivingLicenceID());
        userDto.setPhotoUserBase64(user.getPhotoUserBase64());

        return userDto;
    }

    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPatronymic(userDto.getPatronymic());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAge(userDto.getAge());
        user.setPassportID(userDto.getPassportID());
        user.setDrivingLicenceID(userDto.getDrivingLicenceID());
        user.setPhotoUserBase64(userDto.getPhotoUserBase64());

        return user;
    }
}
