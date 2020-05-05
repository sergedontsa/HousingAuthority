package com.housing.authority.Resources;

import java.util.Calendar;
import java.util.Random;

public class ID_Utils {

    public static  String COMPLAIN_ID(){
        try {
            Thread.sleep(100);
        }catch (InterruptedException exception){
            exception.printStackTrace();
        }
        return String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(5, 12)
                +Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public static String APARTMENT_ID() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "APT-"+String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(5, 12)
                +"-"+Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }


    public static String BUILDING_ID() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "HAB-"+String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(5, 12)
                +"-"+Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

    }


    public static String EMPLOYEE_ID() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "HAE-"+String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(0,3)+getSignature(3);
    }


    public static String TENANT_ID(){
        try {
            Thread.sleep(100);
        }catch (InterruptedException exception){
            exception.printStackTrace();
        }

        return "HAT"+String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(0,3)+getSignature(4);
    }

    public static String USER_ID() {
        try {
            Thread.sleep(100);

        }catch (InterruptedException exception){
            exception.printStackTrace();
        }
        return String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(0,3)+getSignature(4);
    }
    private static String getSignature(int num){
        String alphabet = "1A2B3C4D5E6F7G8H7I10J11K12L13M14N15O16P17Q18R19S20T21";
        Random r = new Random();
        StringBuilder signature = new StringBuilder();
        for(int x = 0; x< num; x++){
            signature.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return new String(signature);
    }

    public static String COMPLAIN_DONE_CONFIRMATION() {
        try {
            Thread.sleep(100);
        }catch (InterruptedException exception){
            exception.printStackTrace();
        }
        return "CD"+String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(5, 12)
                +Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public static String RECORD_ID() {
        try {
            Thread.sleep(100);
        }catch (InterruptedException exception){
            exception.printStackTrace();
        }
        return "HARC"+String.valueOf(Calendar.getInstance().getTimeInMillis()).substring(5, 12)
                +Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
}
