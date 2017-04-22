package com.example.tanmay.vitringtonecontroller.Boundary.Database;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tanmay on 17/3/17.
 */

public class TimetableData {
    static Boolean theoryTimeTable[][]=new Boolean[5][11];
    static Boolean labTimeTable[][]=new Boolean[5][11];

    static Map<String,List> theoryTimeTableMap=new HashMap<String, List>();
    static List<Integer> A1position = Arrays.asList(0,1);

    public static void initialize(){
        theoryTimeTableMap.put("A1",A1position);
    }
}