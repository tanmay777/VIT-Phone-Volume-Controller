package com.example.tanmay.vitringtonecontroller.Entity.Actors;

/**
 * Created by tanmay on 22/4/17.
 */

public class status {

    static String message="Invalid Credentials";
    static String code;


    public static void setMessage(String message) {
        status.message= message;
    }

    public static void setCode(String code) {
        status.code = code;
    }

    public static String getMessage() {
        return message;
    }

    public static String getCode() {
        return code;
    }

}
