package ru.lebedev.servicecarsharingusers.model;

import lombok.Data;
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
    private String passportID;
    private String drivingLicenceID;
    @Column(name = "photo_user", columnDefinition = "BLOB")
    private byte[] photo;
}
