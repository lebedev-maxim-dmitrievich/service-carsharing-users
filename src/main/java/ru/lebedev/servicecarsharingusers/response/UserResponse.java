package ru.lebedev.servicecarsharingusers.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class UserResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String password;
    private int age;
    private String passportID;
    private String drivingLicenceID;
    @JsonAlias("photo_user")
    private byte[] photo;
}
