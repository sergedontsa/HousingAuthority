package com.housing.authority.Resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;


public class Utilities {


    public static long getNumDays(String start, String end) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy");
        Date date1, date2;
        try {
            date1 = simpleDateFormat.parse(start);
            date2 = simpleDateFormat.parse(end);
            return  (date2.getTime() - date1.getTime())/(1000*60*60*24);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
