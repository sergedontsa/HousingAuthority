package com.housing.authority.Resources;

import java.util.Calendar;

/**
 *
 */

public class Constant {

    public static final int REGULAR_USER_ROLE_CODE = 1;
    public static final int ADMINISTRATOR_ROLE_CODE = 1;
    public static final String EMAIL_API_KEY = "4379295234684f1cf23543d6d288ba3";
    public static final String EMAIL_API_SECURITY_KEY = "3a0c7b17780f4cd6f22d0cd114205326";

    //get current date as String
    public static String getCurrentDateAsString(){
        return    String.valueOf(Calendar.getInstance().getTime());
    }

    public final static String HOUSING_CONTROLLER= "/housing/authority/v1";

    public final static String CONSUMES = "application/json";
    public final static String PRODUCE = "application/json";

    //Employee
    public final static String EMPLOYEE_GET_ALL = "employee/all";
    public final static String EMPLOYEE_GET_WITH_ID = "employee/{id}";
    public final static String EMPLOYEE_DELETE_WITH_ID = "employee/delete/{id}";
    public final static String EMPLOYEE_UPDATE_WITH_ID = "employee/update/{id}";
    public final static String EMPLOYEE_SAVE = "employee/save";

    //Building
    public final static String BUILDING_GET_ALL = "building/all";
    public final static String BUILDING_GET_WITH_ID = "building/{id}";
    public final static String BUILDING_DELETE_WITH_ID = "building/delete/{id}";
    public final static String BUILDING_UPDATE_WITH_ID = "building/update/{id}";
    public final static String BUILDING_SAVE = "building/save";

    //Users
    public final static String USER_GET_ALL = "user/all";
    public final static String USER_GET_WITH_ID = "user/{id}";
    public final static String USER_DELETE_WITH_ID = "user/delete/{id}";
    public final static String USER_UPDATE_WITH_ID = "user/update";
    public final static String USER_SAVE = "user/save";

    //Apartment
    public final static String APARTMENT_GET_ALL = "apartment/all";
    public final static String APARTMENT_GET_WITH_ID = "apartment/{id}";
    public final static String APARTMENT_DELETE_WITH_ID = "apartment/delete/{id}";
    public final static String APARTMENT_UPDATE_WITH_ID = "apartment/update/{id}";
    public final static String APARTMENT_SAVE = "apartment/save";

    //Lender
    public final static String TENANT_GET_ALL = "tenant/all";
    public final static String TENANT_GET_WITH_ID = "tenant/{id}";
    public final static String TENANT_DELETE_WITH_ID = "tenant/delete/{id}";
    public final static String TENANT_UPDATE_WITH_ID = "tenant/update/{id}";
    public final static String TENANT_SAVE = "tenant/save";

    //Complain
    public final static String COMPLAIN_GET_ALL = "complain/all";
    public final static String COMPLAIN_GET_WITH_ID = "complain/{id}";
    public final static String COMPLAIN_DELETE_WITH_ID = "complain/delete/{id}";
    public final static String COMPLAIN_UPDATE_WITH_ID = "complain/update/{id}";
    public final static String COMPLAIN_SAVE = "complain/save";

    //Complain Done
    public final static String COMPLAIN_DONE_GET_ALL = "complaindone/all";
    public final static String COMPLAIN_DONE_GET_WITH_ID = "complaindone/{id}";
    public final static String COMPLAIN_DONE_DELETE_WITH_ID = "complaindone/delete/{id}";
    public final static String COMPLAIN_DONE_UPDATE_WITH_ID = "complaindone/update/{id}";
    public final static String COMPLAIN_DONE_SAVE = "complaindone/save";

    //Personal Information
    public final static String PERSONAL_INFORMATION_GET_ALL = "record/all";
    public final static String PERSONAL_INFORMATION_GET_WITH_ID = "record/{id}";
    public final static String PERSONAL_INFORMATION_DELETE_WITH_ID = "record/delete/{id}";
    public final static String PERSONAL_INFORMATION_UPDATE_WITH_ID = "record/update/{id}";
    public final static String PERSONAL_INFORMATION_SAVE = "record/save";
}
