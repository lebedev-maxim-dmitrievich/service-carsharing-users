package ru.lebedev.servicecarsharingusers.model;

import lombok.Data;
import ru.lebedev.servicecarsharingusers.model.enums.UserStatus;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String password;
    private int age;
    @Column(name = "passport_id")
    private String passportID;
    @Column(name = "driving_licence_id")
    private String drivingLicenceID;
    @Column(name = "photo_user", length=83886080)
    private UserStatus status;
    private byte[] photo;
}
