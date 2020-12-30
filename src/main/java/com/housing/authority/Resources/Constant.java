package com.housing.authority.Resources;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * This class provide all implemented endpoint
 * By: Serge Dontsa
 * Montreal, Canada
 */

@Component
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

    public final static String HOUSING_CONTROLLER= "/vertical/v1";

    public final static String CONSUMES = MediaType.APPLICATION_JSON_VALUE;
    public final static String PRODUCE = MediaType.APPLICATION_JSON_VALUE;

    //Employee
    public final static String EMPLOYEE_CONTROLLER = "/vertical/v1/employee";
    public final static String EMPLOYEE_GET_ALL = "get/all";
    public final static String EMPLOYEE_GET_WITH_ID = "get/{id}";
    public final static String EMPLOYEE_DELETE_WITH_ID = "/delete/{id}";
    public final static String EMPLOYEE_UPDATE_WITH_ID = "/update/{id}";
    public final static String EMPLOYEE_SAVE = "/save";

    //Building
    public final static String BUILDING_CONTROLLER = "/vertical/v1/building";
    public final static String BUILDING_GET_ALL = "get/all";
    public final static String BUILDING_GET_WITH_ID = "get/{id}";
    public final static String BUILDING_DELETE_WITH_ID = "/delete/{id}";
    public final static String BUILDING_UPDATE_WITH_ID = "/update/{buildingId}";
    public final static String BUILDING_SAVE = "/save";

    //Building document
    public final static String BUILDING_DOCUMENT_CONTROLLER = "/vertical/v1/building/doc";
    public final static String BUILDING_DOCUMENT_GET_ALL = "get/all";
    public final static String BUILDING_DOCUMENT_GET_WITH_BUILDING_ID = "/{buildingId}/{docType}/get";
    public final static String BUILDING_DOCUMENT_DELETE_WITH_BUILDING_ID = "/{buildingId}/delete";
    public final static String BUILDING_DOCUMENT_UPDATE_WITH_BUILDING_ID = "/{buildingId}/update";
    public final static String BUILDING_DOCUMENT_SAVE_WITH_BUILDING_ID = "/{buildingId}/{docType}/save";

    //Users
    public final static String USER_CONTROLLER = "/vertical/v1/user";
    public final static String USER_GET_ALL = "get/all";
    public final static String USER_GET_WITH_ID = "get/{id}";
    public final static String USER_DELETE_WITH_ID = "/delete/{id}";
    public final static String USER_UPDATE_WITH_ID = "/update/{id}";
    public final static String USER_SAVE = "/save";

    //Apartment
    public final static String APARTMENT_CONTROLLER = "/vertical/v1/apartment";
    public final static String APARTMENT_GET_ALL = "get/all";
    public final static String APARTMENT_GET_WITH_ID = "get/{id}";
    public final static String APARTMENT_DELETE_WITH_ID = "/delete/{apartmentId}";
    public final static String APARTMENT_UPDATE_WITH_ID = "/update/{buildingId}/{id}";
    public final static String APARTMENT_SAVE = "/{buildingId}/save";

    //Apartment Dimension
    public final static String APARTMENT_DIMENSION_CONTROLLER = "/vertical/v1/apartment/dimension";
    public final static String APARTMENT_DIMENSION_GET_ALL = "get/all";
    public final static String APARTMENT_DIMENSION_GET_WITH_APARTMENT_ID = "/get/{apartmentId}";
    public final static String APARTMENT_DIMENSION_DELETE_WITH_APARTMENT_ID = "/{apartmentId}/delete";
    public final static String APARTMENT_DIMENSION_UPDATE_WITH_APARTMENT_ID = "/{apartmentId}/update";
    public final static String APARTMENT_DIMENSION_SAVE_WITH_APARTMENT_ID = "/{apartmentId}//save";

    //Apartment Fee
    public final static String APARTMENT_FEE_CONTROLLER = "/vertical/v1/apartment/fee/";
    public final static String APARTMENT_FEE_GET_ALL = "get/all";
    public final static String APARTMENT_FEE_GET_WIT_APARTMENT_ID = "/get/{apartmentId}";
    //public final static String APARTMENT_FEE_DELETE_WITH_APARTMENT_ID = "/{apartmentId}/delete";
    public final static String APARTMENT_FEE_UPDATE_WITH_APARTMENT_ID = "/{apartmentId}/update";
    public final static String APARTMENT_FEE_SAVE_WITH_APARTMENT_ID = "/{apartmentId}/save";

    //Apartment Expense
    public final static String APARTMENT_EXPENSE_CONTROLLER = "/vertical/v1/apartment/expense";
    public final static String APARTMENT_EXPENSE_GET_ALL = "get/all";
    public final static String APARTMENT_EXPENSE_GET_WITH_ID = "/get/{expenseId}";
    public final static String APARTMENT_EXPENSE_DELETE_WITH_ID = "/{employeeId}/{expenseId}/{apartmentId}/{buildingId}/delete";
    public final static String APARTMENT_EXPENSE_UPDATE_WITH_ID = "/{employeeId}/{expenseId}/{apartmentId}/{buildingId}/update";
    public final static String APARTMENT_EXPENSE_CREATE = "/{employeeId}/{apartmentId}/{buildingId}/save";

    //Apartment Document
    public final static String APARTMENT_DOCUMENT_CONTROLLER = "/vertical/v1/apartment/doc";
    public final static String APARTMENT_DOCUMENT_GET_ALL = "get/all";
    public final static String APARTMENT_DOCUMENT_GET_WITH_APARTMENT_ID = "/{apartmentId}/{docType}/get";
    public final static String APARTMENT_DOCUMENT_DELETE_WITH_APARTMENT_ID = "/{apartmentId}/delete";
    public final static String APARTMENT_DOCUMENT_UPDATE_WITH_APARTMENT_ID = "/{apartmentId}/update";
    public final static String APARTMENT_DOCUMENT_SAVE_WITH_APARTMENT_ID = "/{apartmentId}/{docType}/save";

    //Tenant
    public final static String TENANT_CONTROLLER = "/vertical/v1/tenant";
    public final static String TENANT_GET_ALL = "get/all";
    public final static String TENANT_GET_WITH_ID = "get/{id}";
    public final static String TENANT_DELETE_WITH_ID = "/delete/{id}";
    public final static String TENANT_UPDATE_WITH_ID = "/update/{id}";
    public final static String TENANT_SAVE = "/{buildingId}/{apartmentid}/save";
    public final static String TENANT_ACTIVATE = "/{tenantId}/{apartmentId}/{buildingId}/active";

    //Tenant Expense
    public final static String TENANT_EXPENSE_CONTROLLER = "/vertical/v1/tenant/expense";
    public final static String TENANT_EXPENSE_GET_ALL = "get/all";
    public final static String TENANT_EXPENSE_GET_WITH_ID = "get/{expenseId}";
    public final static String TENANT_EXPENSE_DELETE_WITH_ID = "/delete/{expenseId}/{tenantId}/{apartmentId}/{buildingId}";
    public final static String TENANT_EXPENSE_UPDATE_WITH_ID = "/update/{expenseId}/{tenantId}/{apartmentId}/{buildingId}";
    public final static String TENANT_EXPENSE_SAVE = "/{tenantId}/{buildingId}/{apartmentId}/save";

    //Tenant Document
    public final static String TENANT_DOCUMENT_CONTROLLER = "/vertical/v1/tenant/doc";
    public final static String TENANT_DOCUMENT_GET_ALL = "get/all";
    public final static String TENANT_DOCUMENT_GET_WITH_TENANT_ID = "/{tenantId}/{docType}/get";
    public final static String TENANT_DOCUMENT_DELETE_WITH_TENANT_ID = "/{tenantId}/delete";
    public final static String TENANT_DOCUMENT_UPDATE_WITH_TENANT_ID = "/{tenantId}/update";
    public final static String TENANT_DOCUMENT_SAVE_WITH_TENANT_ID = "/{tenantId}/{docType}/save";


    //Complain
    public final static String COMPLAIN_CONTROLLER = "/vertical/v1/complain";
    public final static String COMPLAIN_GET_ALL = "get/all";
    public final static String COMPLAIN_GET_WITH_ID = "get/{id}";
    public final static String COMPLAIN_DELETE_WITH_ID = "/delete/{id}";
    public final static String COMPLAIN_UPDATE_WITH_ID = "/update/{id}";
    public final static String COMPLAIN_SAVE = "/save";

    //Complain Done
    public final static String COMPLAIN_DONE_CONTROLLER = "/vertical/v1/complain/done";
    public final static String COMPLAIN_DONE_GET_ALL = "get/all";
    public final static String COMPLAIN_DONE_GET_WITH_ID = "get/{id}";
    public final static String COMPLAIN_DONE_DELETE_WITH_ID = "/delete/{id}";
    public final static String COMPLAIN_DONE_UPDATE_WITH_ID = "/update/{id}";
    public final static String COMPLAIN_DONE_SAVE = "/save";

    //Personal Information
    public final static String PERSONAL_INFORMATION_CONTROLLER = "/vertical/v1/record/";
    public final static String PERSONAL_INFORMATION_GET_ALL = "get/all";
    public final static String PERSONAL_INFORMATION_GET_WITH_ID = "get/{id}";
    public final static String PERSONAL_INFORMATION_DELETE_WITH_ID = "/delete/{id}";
    public final static String PERSONAL_INFORMATION_UPDATE_WITH_ID = "/update/{id}";
    public final static String PERSONAL_INFORMATION_SAVE = "/save";

    //Listening
    public final static String LISTENING_CONTROLLER ="/vertical/v1/listening/";
    public final static String LISTENING_GET_ALL = "get/all";
    public final static String LISTENING_GET_WITH_ID = "get/{id}";
    public final static String LISTENING_DELETE_WITH_ID = "/delete/{id}";
    public final static String LISTENING_UPDATE_WITH_ID = "/update/{id}";

    //Subscriber
    public final static String SUBSCRIBER_CONTROLLER = "/vertical/v1/subscriber";
    public final static String SUBSCRIBER_GET_ALL = "get/all";
    public final static String SUBSCRIBER_GET_WITH_ID = "get/{id}";
    public final static String SUBSCRIBER_DELETE_WITH_ID = "/delete/{id}";
    public final static String SUBSCRIBER_UPDATE_WITH_ID = "/update/{id}";
    public final static String SUBSCRIBER_SAVE = "/save";

    //Billing
    public final static String BILLING_CONTROLLER = "/vertical/v1/billing";
    public final static String BILLING_GET_ALL = "get/all";
    public final static String BILLING_GET_WITH_ID = "get/{id}";
    public final static String BILLING_DELETE_WITH_ID = "/delete/{id}/{tenantid}";
    public final static String BILLING_UPDATE_WITH_ID = "/update/{id}";
    public final static String BILLING_SAVE = "/{tenantId}/{apartmentId}/{buildingId}/save";
    public final static String BILLING_BY_TENANT_ID = "/{tenantid}/get";

    //Schedule
    public final static String SCHEDULE_CONTROLLER = "/vertical/v1/schedule";
    public final static String SCHEDULE_GET_ALL = "get/all";
    public final static String SCHEDULE_GET_WITH_ID = "get/{id}";
    public final static String SCHEDULE_DELETE_WITH_ID = "/delete/{id}";
    public final static String SCHEDULE_UPDATE_WITH_ID = "/update/{id}";
    public final static String SCHEDULE_SAVE = "/save";

    //Employee Detail
    public final static String EMPLOYEE_DETAIL_CONTROLLER = "/vertical/v1/employee/detail";
    public final static String EMPLOYEE_DETAIL_GET_ALL = "get/all";
    public final static String EMPLOYEE_DETAIL_GET_WITH_EMPLOYEE_ID = "/get/{employeeid}";
    public final static String EMPLOYEE_DETAIL_DELETE_WITH_ID = "/{employeeid}/delete";
    public final static String EMPLOYEE_DETAIL_UPDATE_WITH_ID = "/{employeeid}/update";
    public final static String EMPLOYEE_DETAIL_SAVE = "/{employeeid}/save";

    //Building address
    public final static String BUILDING_ADDRESS_CONTROLLER = "/vertical/v1/building/address";
    public final static String BUILDING_ADDRESS_GET_ALL = "get/all";
    public final static String BUILDING_ADDRESS_GET_WITH_BUILDING_ID = "/get/{buildingId}/";
    public final static String BUILDING_ADDRESS_DELETE_WITH_BUILDING_ID = "/{buildingId}/delete";
    public final static String BUILDING_ADDRESS_UPDATE_WITH_BUILDING_ID = "/{buildingId}/update";
    public final static String BUILDING_ADDRESS_SAVE_WITH_BUILDING_ID = "/{buildingId}/save";

    //Employee address
    public final static String EMPLOYEE_ADDRESS_CONTROLLER = "/vertical/v1/employee/address";
    public final static String EMPLOYEE_ADDRESS_GET_ALL = "get/all";
    public final static String EMPLOYEE_ADDRESS_GET_WITH_EMPLOYEE_ID = "/get/{employeeId}";
    public final static String EMPLOYEE_ADDRESS_DELETE_WITH_EMPLOYEE_ID = "/{employeeId}/delete";
    public final static String EMPLOYEE_ADDRESS_UPDATE_WITH_EMPLOYEE_ID = "/{employeeId}/update";
    public final static String EMPLOYEE_ADDRESS_SAVE_WITH_EMPLOYEE_ID = "/{employeeId}/save";

    //Employee Document
    public final static String EMPLOYEE_DOCUMENT_CONTROLLER = "/vertical/v1/employee/doc";
    public final static String EMPLOYEE_DOCUMENT_GET_ALL = "get/all";
    public final static String EMPLOYEE_DOCUMENT_GET_WITH_EMPLOYEE_ID = "/{employeeId}/{docType}/get";
    public final static String EMPLOYEE_DOCUMENT_DELETE_WITH_EMPLOYEE_ID = "/{employeeId}/delete";
    public final static String EMPLOYEE_DOCUMENT_UPDATE_WITH_EMPLOYEE_ID = "/{employeeId}/update";
    public final static String EMPLOYEE_DOCUMENT_SAVE_WITH_EMPLOYEE_ID = "/{employeeId}/{docType}/save";

    //Contact Information
    public final static String CONTACT_INFORMATION_CONTROLLER = "/vertical/v1/contact/information";
    public final static String CONTACT_INFORMATION_GET_ALL = "get/all";
    public final static String CONTACT_INFORMATION_GET_ONE = "/get/{phoneNumber}";
    public final static String CONTACT_INFORMATION_DELETE = "/{phoneNumber}/delete";
    public final static String CONTACT_INFORMATION_UPDATE = "/{phoneNumber}/update";
    public final static String CONTACT_INFORMATION_SAVE = "/{phoneNumber}/save";
}
