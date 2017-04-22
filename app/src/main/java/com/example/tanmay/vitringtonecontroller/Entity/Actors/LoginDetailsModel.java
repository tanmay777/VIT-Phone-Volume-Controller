package com.example.tanmay.vitringtonecontroller.Entity.Actors;

/**
 * Created by tanmay on 22/4/17.
 */

public class LoginDetailsModel {

    static String regnos;
    static String campus;
    static status status;

    public static void setStatus(status status) {
        LoginDetailsModel.status = status;
    }

    public static status getStatus() {

        return status;
    }

    public static void setRegnos(String regnos) {
        LoginDetailsModel.regnos = regnos;
    }

    public static void setCampus(String campus) {
        LoginDetailsModel.campus = campus;
    }

    public static String getRegnos() {

        return regnos;
    }

    public static String getCampus() {
        return campus;
    }


    public LoginDetailsModel() {

    }

    /*

    {
        "regno": "15BCE0618",
            "campus": "vellore",
            "status": {
        "message": "Success",
                "code": 0
    }
    }*/

}
