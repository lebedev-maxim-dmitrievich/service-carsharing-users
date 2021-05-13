package ru.lebedev.servicecarsharingusers.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import ru.lebedev.servicecarsharingusers.constant.UserRequestConstants;

import javax.validation.constraints.*;

@Data
public class UserRequest implements UserRequestConstants {

    @NotEmpty
    @Pattern(regexp = REG_EX_FIRST_NAME)
    private String firstName;

    @NotEmpty
    @Pattern(regexp = REG_EX_LAST_NAME)
    private String lastName;

    @Pattern(regexp = REG_EX_PATRONYMIC)
    private String patronymic;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 5, max = 20)
    private String password;

    @Min(21)
    private int age;

    @NotEmpty
    @Pattern(regexp = REG_EX_PASSPORT)
    private String passportID;

    @NotEmpty
    @Pattern(regexp = REG_EX_DRIVER_LICENSE)
    private String drivingLicenceID;

    @JsonAlias("photo_user")
    private byte[] photo;
}
