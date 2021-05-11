package ru.lebedev.servicecarsharingusers.dto;

public class UserDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private int age;
    private String passportID;
    private String drivingLicenceID;
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

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public String getDrivingLicenceID() {
        return drivingLicenceID;
    }

    public void setDrivingLicenceID(String drivingLicenceID) {
        this.drivingLicenceID = drivingLicenceID;
    }

    public String getPhotoUserBase64() {
        return photoUserBase64;
    }

    public void setPhotoUserBase64(String photoUserBase64) {
        this.photoUserBase64 = photoUserBase64;
    }
}
