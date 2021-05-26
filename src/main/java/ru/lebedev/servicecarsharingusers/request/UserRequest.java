package ru.lebedev.servicecarsharingusers.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserRequest {

    private final String REG_EX_PASSPORT = "\\d{4}\\s\\d{6}";
    private final String REG_EX_DRIVER_LICENSE = "\\d{4}\\s\\d{6}";
    private final String REG_EX_FIRST_NAME = "^(\\G[А-Я]{1}[а-яё]+)";
    private final String REG_EX_LAST_NAME = "^(\\G[А-Я]{1}[а-яё]+)";
    private final String REG_EX_PATRONYMIC = "^(\\G[А-Я]{1}[а-яё]+)";

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
    private String passportId;

    @NotEmpty
    @Pattern(regexp = REG_EX_DRIVER_LICENSE)
    private String drivingLicenceId;

    @JsonAlias("photo_user")
    private byte[] photo;
}
