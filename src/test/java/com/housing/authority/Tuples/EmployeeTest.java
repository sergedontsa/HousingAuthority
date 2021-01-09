package com.housing.authority.Tuples;

import com.housing.authority.Enum.EmployeeStatus;
import com.housing.authority.Resources.Constant;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeTest {

    private Map<String, Object> sample_employee;

    @BeforeAll
    private void init(){

        this.sample_employee = new HashMap<>();
        sample_employee.put("department", "Developer");
        sample_employee.put("role", "Manager");
        sample_employee.put("status", String.valueOf(EmployeeStatus.ACTIVE));
    }

    @Nested
    class saveTestClass {
        @Test
        @DisplayName(">>Save One Test")
        @Order(1)
        void saveTest() {
            String url_save = Constant.DOMAIN+Constant.EMPLOYEE_CONTROLLER+Constant.EMPLOYEE_SAVE;
            given().contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(EmployeeTest.this.sample_employee)
                    .when().post(url_save)
                    .then().statusCode(201)
                    .log()
                    .all();
        }
    }

    @Nested
    class readOneTestClass{
        @Test
        @DisplayName(">>Read One Test")
        @Order(2)
        void testReadOne(){
            String url_read = Constant.DOMAIN+Constant.EMPLOYEE_CONTROLLER+Constant.EMPLOYEE_GET_WITH_ID;
            given().pathParam("id", "EMP1605O1")
                    .when().get(url_read)
                    .then().statusCode(200)
                    .log()
                    .all();
        }

    }
    @Nested
    class ReadAllTestClass{
        @Test
        void testReadAll(){
            String url_read_all = Constant.DOMAIN+Constant.EMPLOYEE_CONTROLLER+Constant.EMPLOYEE_GET_ALL;
            given().get(url_read_all)
                    .then()
                    .statusCode(200);
        }
    }
    @Nested class DeleteTestClass{
        @Test
        void testDelete(){

        }
    }
}