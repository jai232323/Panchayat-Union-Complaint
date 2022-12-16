package com.example.puc.modal;

public class Users {

    private String UserImage,Name,Email,Password,Address,MobileNumber,Gender,DOB,UserId,UniqueKey;

    public Users(){

    }

    public Users(String userImage, String name, String email, String password, String address, String mobileNumber, String gender, String DOB, String userId, String uniqueKey) {
        UserImage = userImage;
        Name = name;
        Email = email;
        Password = password;
        Address = address;
        MobileNumber = mobileNumber;
        Gender = gender;
        this.DOB = DOB;
        UserId = userId;
        UniqueKey = uniqueKey;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUniqueKey() {
        return UniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        UniqueKey = uniqueKey;
    }
}
