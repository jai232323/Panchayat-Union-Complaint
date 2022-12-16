package com.example.puc.modal;

public class Complaints {

    private String C_Name,C_MobileNumber,C_Address,C_Village,C_Ward,C_Taluk,C_ProblemPhoto,C_ProofPhoto,C_Box,UserId,C_id,Date,Time;

    private String Result,User_MobileNumber;

    public Complaints() {
    }

    public Complaints(String c_Name, String c_MobileNumber, String c_Address, String c_Village, String c_Ward,
                      String c_Taluk, String c_ProblemPhoto, String c_ProofPhoto, String c_Box, String userId,
                      String c_id, String date, String time, String result, String user_MobileNumber) {
        C_Name = c_Name;
        C_MobileNumber = c_MobileNumber;
        C_Address = c_Address;
        C_Village = c_Village;
        C_Ward = c_Ward;
        C_Taluk = c_Taluk;
        C_ProblemPhoto = c_ProblemPhoto;
        C_ProofPhoto = c_ProofPhoto;
        C_Box = c_Box;
        UserId = userId;
        C_id = c_id;
        Date = date;
        Time = time;
        Result = result;
        User_MobileNumber = user_MobileNumber;
    }

    public String getC_Name() {
        return C_Name;
    }

    public void setC_Name(String c_Name) {
        C_Name = c_Name;
    }

    public String getC_MobileNumber() {
        return C_MobileNumber;
    }

    public void setC_MobileNumber(String c_MobileNumber) {
        C_MobileNumber = c_MobileNumber;
    }

    public String getC_Address() {
        return C_Address;
    }

    public void setC_Address(String c_Address) {
        C_Address = c_Address;
    }

    public String getC_Village() {
        return C_Village;
    }

    public void setC_Village(String c_Village) {
        C_Village = c_Village;
    }

    public String getC_Ward() {
        return C_Ward;
    }

    public void setC_Ward(String c_Ward) {
        C_Ward = c_Ward;
    }

    public String getC_Taluk() {
        return C_Taluk;
    }

    public void setC_Taluk(String c_Taluk) {
        C_Taluk = c_Taluk;
    }

    public String getC_ProblemPhoto() {
        return C_ProblemPhoto;
    }

    public void setC_ProblemPhoto(String c_ProblemPhoto) {
        C_ProblemPhoto = c_ProblemPhoto;
    }

    public String getC_ProofPhoto() {
        return C_ProofPhoto;
    }

    public void setC_ProofPhoto(String c_ProofPhoto) {
        C_ProofPhoto = c_ProofPhoto;
    }

    public String getC_Box() {
        return C_Box;
    }

    public void setC_Box(String c_Box) {
        C_Box = c_Box;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getC_id() {
        return C_id;
    }

    public void setC_id(String c_id) {
        C_id = c_id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getUser_MobileNumber() {
        return User_MobileNumber;
    }

    public void setUser_MobileNumber(String user_MobileNumber) {
        User_MobileNumber = user_MobileNumber;
    }
}
