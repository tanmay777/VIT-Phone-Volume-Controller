package com.example.tanmay.vitringtonecontroller.Boundary.Database;

import android.widget.CheckBox;

/**
 * Created by tanmay on 7/3/17.
 */

public class BuildingInformation {
    public static void setMainBuildingCheckbox(Boolean mainBuildingCheckbox) {
        BuildingInformation.mainBuildingCheckbox = mainBuildingCheckbox;
    }

    public static void setCdmmCheckBox(Boolean cdmmCheckBox) {
        BuildingInformation.cdmmCheckBox = cdmmCheckBox;
    }

    public static void setGdnCheckBox(Boolean gdnCheckBox) {
        BuildingInformation.gdnCheckBox = gdnCheckBox;
    }

    public static void setLibraryCheckBox(Boolean libraryCheckBox) {
        BuildingInformation.libraryCheckBox = libraryCheckBox;
    }

    public static void setSmvCheckBox(Boolean smvCheckBox) {
        BuildingInformation.smvCheckBox = smvCheckBox;
    }

    public static void setTtCheckBox(Boolean ttCheckBox) {
        BuildingInformation.ttCheckBox = ttCheckBox;
    }

    public static void setSjtCheckBox(Boolean sjtCheckBox) {
        BuildingInformation.sjtCheckBox = sjtCheckBox;
    }

    public static Boolean getMainBuildingCheckbox() {

        return mainBuildingCheckbox;
    }

    public static Boolean getCdmmCheckBox() {
        return cdmmCheckBox;
    }

    public static Boolean getGdnCheckBox() {
        return gdnCheckBox;
    }

    public static Boolean getLibraryCheckBox() {
        return libraryCheckBox;
    }

    public static Boolean getSmvCheckBox() {
        return smvCheckBox;
    }

    public static Boolean getTtCheckBox() {
        return ttCheckBox;
    }

    public static Boolean getSjtCheckBox() {
        return sjtCheckBox;
    }

    public BuildingInformation() {

    }

    static Boolean mainBuildingCheckbox;
    static Boolean cdmmCheckBox;
    static Boolean gdnCheckBox;
    static Boolean libraryCheckBox;
    static Boolean smvCheckBox;
    static Boolean ttCheckBox;
    static Boolean sjtCheckBox;

    public static void setTurnon(Boolean turnon) {
        BuildingInformation.turnon = turnon;
    }

    public static Boolean getTurnon() {

        return turnon;
    }

    static Boolean turnon;

}
