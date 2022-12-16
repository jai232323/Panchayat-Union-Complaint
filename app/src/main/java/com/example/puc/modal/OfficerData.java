package com.example.puc.modal;

public class OfficerData {

    private String OfficerId,OfficerName,OfficerMobilenumber,OfficerPassword,OfficerVillage,Date,Time,Status;

    public OfficerData() {
    }

    public OfficerData(String officerId, String officerName, String officerMobilenumber,
                       String officerPassword, String officerVillage, String date, String time, String status) {
        OfficerId = officerId;
        OfficerName = officerName;
        OfficerMobilenumber = officerMobilenumber;
        OfficerPassword = officerPassword;
        OfficerVillage = officerVillage;
        Date = date;
        Time = time;
        Status = status;
    }

    public String getOfficerId() {
        return OfficerId;
    }

    public void setOfficerId(String officerId) {
        OfficerId = officerId;
    }

    public String getOfficerName() {
        return OfficerName;
    }

    public void setOfficerName(String officerName) {
        OfficerName = officerName;
    }

    public String getOfficerMobilenumber() {
        return OfficerMobilenumber;
    }

    public void setOfficerMobilenumber(String officerMobilenumber) {
        OfficerMobilenumber = officerMobilenumber;
    }

    public String getOfficerPassword() {
        return OfficerPassword;
    }

    public void setOfficerPassword(String officerPassword) {
        OfficerPassword = officerPassword;
    }

    public String getOfficerVillage() {
        return OfficerVillage;
    }

    public void setOfficerVillage(String officerVillage) {
        OfficerVillage = officerVillage;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
