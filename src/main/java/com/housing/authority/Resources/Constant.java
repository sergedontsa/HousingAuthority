package com.housing.authority.Resources;

import java.util.Calendar;

/**
 * This class provide all implemented endpoint
 * By: Serge Dontsa
 * Montreal, Canada
 */

public class Constant {

    //SendGrid
    public static final String emailApi = "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";


    public static final int REGULAR_USER_ROLE_CODE = 1;
    public static final int ADMINISTRATOR_ROLE_CODE = 1;
    public static final String EMAIL_API_KEY = "4379295234684f1cf23543d6d288ba3";
    public static final String EMAIL_API_SECURITY_KEY = "3a0c7b17780f4cd6f22d0cd114205326";

    public static final String GMAIL_CLIENT_ID = "718352573405-4s729kdebhdujddv3pjd693tte0klq5l.apps.googleusercontent.com";
    public static final String CLIENT_SECRET = "uOE_p0Eihp2QkyWkVNcb_Yib";

    //get current date as String
    public static String getCurrentDateAsString(){
        return    String.valueOf(Calendar.getInstance().getTime());
    }

    public final static String HOUSING_CONTROLLER= "/housing/authority/v1";

    public final static String CONSUMES = "application/json";
    public final static String PRODUCE = "application/json";

    //Employee
    public final static String EMPLOYEE_CONTROLLER = "/housing/authority/v1/employee";
    public final static String EMPLOYEE_GET_ALL = "/all";
    public final static String EMPLOYEE_GET_WITH_ID = "get/{id}";
    public final static String EMPLOYEE_DELETE_WITH_ID = "/delete/{id}";
    public final static String EMPLOYEE_UPDATE_WITH_ID = "/update/{id}";
    public final static String EMPLOYEE_SAVE = "/save";

    //Building
    public final static String BUILDING_CONTROLLER = "/housing/authority/v1/building";
    public final static String BUILDING_GET_ALL = "/all";
    public final static String BUILDING_GET_WITH_ID = "get/{id}";
    public final static String BUILDING_DELETE_WITH_ID = "/delete/{id}";
    public final static String BUILDING_UPDATE_WITH_ID = "/update/{id}";
    public final static String BUILDING_SAVE = "/save";

    //Users
    public final static String USER_CONTROLLER = "/housing/authority/v1/user";
    public final static String USER_GET_ALL = "/all";
    public final static String USER_GET_WITH_ID = "get/{id}";
    public final static String USER_DELETE_WITH_ID = "/delete/{id}";
    public final static String USER_UPDATE_WITH_ID = "/update/{id}";
    public final static String USER_SAVE = "/save";

    //Apartment
    public final static String APARTMENT_CONTROLLER = "/housing/authority/v1/apartment";
    public final static String APARTMENT_GET_ALL = "/all";
    public final static String APARTMENT_GET_WITH_ID = "get/{id}";
    public final static String APARTMENT_DELETE_WITH_ID = "/delete/{id}";
    public final static String APARTMENT_UPDATE_WITH_ID = "/update/{id}";
    public final static String APARTMENT_SAVE = "/save";

    //Tenant
    public final static String TENANT_CONTROLLER = "/housing/authority/v1/tenant";
    public final static String TENANT_GET_ALL = "/all";
    public final static String TENANT_GET_WITH_ID = "get/{id}";
    public final static String TENANT_DELETE_WITH_ID = "/delete/{id}";
    public final static String TENANT_UPDATE_WITH_ID = "/update/{id}";
    public final static String TENANT_SAVE = "/save";

    //Complain
    public final static String COMPLAIN_CONTROLLER = "/housing/authority/v1/complain";
    public final static String COMPLAIN_GET_ALL = "/all";
    public final static String COMPLAIN_GET_WITH_ID = "get/{id}";
    public final static String COMPLAIN_DELETE_WITH_ID = "/delete/{id}";
    public final static String COMPLAIN_UPDATE_WITH_ID = "/update/{id}";
    public final static String COMPLAIN_SAVE = "/save";

    //Complain Done
    public final static String COMPLAIN_DONE_CONTROLLER = "/housing/authority/v1/complain/done";
    public final static String COMPLAIN_DONE_GET_ALL = "/all";
    public final static String COMPLAIN_DONE_GET_WITH_ID = "get/{id}";
    public final static String COMPLAIN_DONE_DELETE_WITH_ID = "/delete/{id}";
    public final static String COMPLAIN_DONE_UPDATE_WITH_ID = "/update/{id}";
    public final static String COMPLAIN_DONE_SAVE = "/save";

    //Personal Information
    public final static String PERSONAL_INFORMATION_CONTROLLER = "/housing/authority/v1/record/";
    public final static String PERSONAL_INFORMATION_GET_ALL = "/all";
    public final static String PERSONAL_INFORMATION_GET_WITH_ID = "get/{id}";
    public final static String PERSONAL_INFORMATION_DELETE_WITH_ID = "/delete/{id}";
    public final static String PERSONAL_INFORMATION_UPDATE_WITH_ID = "/update/{id}";
    public final static String PERSONAL_INFORMATION_SAVE = "/save";

    //Listening
    public final static String LISTENING_GET_ALL = "listening/all";
    public final static String LISTENING_GET_WITH_ID = "listening/{id}";
    public final static String LISTENING_DELETE_WITH_ID = "listening/delete/{id}";
    public final static String LISTENING_UPDATE_WITH_ID = "listening/update/{id}";

    //Subscriber
    public final static String SUBSCRIBER_CONTROLLER = "/housing/authority/v1/subscriber";
    public final static String SUBSCRIBER_GET_ALL = "/all";
    public final static String SUBSCRIBER_GET_WITH_ID = "get/{id}";
    public final static String SUBSCRIBER_DELETE_WITH_ID = "/delete/{id}";
    public final static String SUBSCRIBER_UPDATE_WITH_ID = "/update/{id}";
    public final static String SUBSCRIBER_SAVE = "/save";
}
