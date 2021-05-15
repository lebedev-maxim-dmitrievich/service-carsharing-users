package ru.lebedev.servicecarsharingusers.response;

import lombok.Data;
import ru.lebedev.servicecarsharingusers.model.enums.UserStatus;

@Data
public class UserResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private int age;
    private byte[] photo;
    private UserStatus status;
}
