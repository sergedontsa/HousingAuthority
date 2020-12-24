package com.housing.authority.Resource;

import com.housing.authority.Resources.IDGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResourceTest {

    @Test
    public void testComplainId(){
        Executable complain_id = IDGenerator::COMPLAIN_ID;
        Executable apartment_id = IDGenerator::APARTMENT_ID;
        Executable building_id = IDGenerator::BUILDING_ID;
        Executable employee_id = IDGenerator::EMPLOYEE_ID;
        Executable tenant_id = IDGenerator::TENANT_ID;
        Executable tenant_sub_id = IDGenerator::TENANT__SUB_ID;
        Executable apartment_expense_id = IDGenerator::APARTMENT_EXPENSE_ID;
        Executable tenant_expense_id = IDGenerator::TENANT_EXPENSE_ID;
        Executable user_id = IDGenerator::USER_ID;
        Executable complain_done_confirmation = IDGenerator::COMPLAIN_DONE_CONFIRMATION;
        Executable record_id = IDGenerator::RECORD_ID;

        assertAll(
                ()-> assertNotNull(complain_id),
                ()-> assertNotNull(apartment_id),
                ()-> assertNotNull(building_id),
                ()-> assertNotNull(employee_id),
                ()-> assertNotNull(tenant_id),
                ()-> assertNotNull(tenant_sub_id),
                ()-> assertNotNull(apartment_expense_id),
                ()-> assertNotNull(tenant_expense_id),
                ()-> assertNotNull(user_id),
                ()-> assertNotNull(complain_done_confirmation),
                ()-> assertNotNull(record_id)

        );
    }
}
