package ru.lebedev.servicecarsharingusers.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "age")
    private int age;
    @Column(name = "passport_id")
    private int passportID;
    @Column(name = "driving_licence_id")
    private int drivingLicenceID;
    @Column(name = "photo_user", columnDefinition = "VARCHAR_IGNORECASE")
    private String photoUserBase64;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPassportID() {
        return passportID;
    }

    public void setPassportID(int passportID) {
        this.passportID = passportID;
    }

    public int getDrivingLicenceID() {
        return drivingLicenceID;
    }

    public void setDrivingLicenceID(int drivingLicenceID) {
        this.drivingLicenceID = drivingLicenceID;
    }

    public String getPhotoUserBase64() {
        return photoUserBase64;
    }

    public void setPhotoUserBase64(String photoUserBase64) {
        this.photoUserBase64 = photoUserBase64;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", passportID=" + passportID +
                ", drivingLicenceID=" + drivingLicenceID +
                ", photoUserBase64='" + photoUserBase64 + '\'' +
                '}';
    }
}
