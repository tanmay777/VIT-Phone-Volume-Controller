package com.example.tanmay.vitringtonecontroller.Entity.Actors;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tanmay on 22/4/17.
 */

public class LoginModel {

    String regno;
    String campus;
    Status status;


    public class Status
    {
        String message;
        String code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public LoginModel(String regNo, String campus, Status status) {
        this.regno = regNo;
        this.campus = campus;
        this.status = status;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getRegno() {

        return regno;
    }

    public Status getStatus() {
        return status;

    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
