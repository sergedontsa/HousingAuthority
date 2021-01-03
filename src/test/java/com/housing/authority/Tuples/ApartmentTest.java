package com.housing.authority.Tuples;


import com.housing.authority.Enum.ApartmentStatus;
import com.housing.authority.Resources.Constant;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApartmentTest {

    private Map<String, Object> sample_apartment;
    private String building_id_for_test;


    @BeforeAll
    public void init(){
        this.building_id_for_test =  "HAB-3275659-5";
        this.sample_apartment = new HashMap<>();
        sample_apartment.put("apartment_number", "1A");
        sample_apartment.put("numbedroom", 2);
        sample_apartment.put("numlivingroom", 2);
        sample_apartment.put("numbathroom", 2);
        sample_apartment.put("numkitchen", 1);
        sample_apartment.put("numcloset", 5);
        sample_apartment.put("numwindows", 10);
        sample_apartment.put("status", ApartmentStatus.Available);
    }



    @Nested
    class saveTestClass{
        @Test
        @DisplayName(">>Save One Test")
        @Order(1)
        void saveTest(){
            String url_save = Constant.DOMAIN+Constant.APARTMENT_CONTROLLER+Constant.APARTMENT_SAVE;
            given().pathParam("buildingId", ApartmentTest.this.building_id_for_test)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(ApartmentTest.this.sample_apartment)
                    .when().post(url_save)
                    .then().statusCode(201)
                    .log().all();
        }
    }
    @Nested
    class ReadOneTestClass{
        @Test
        @DisplayName(">>Read One test")
        @Order(2)
        void testReadOne() throws ParseException {
            String url = "http://localhost:1000/vertical/v1/apartment/get/{id}";
            given().pathParam("id", "APT-1529293-4")
                    .when().get(url)
                    .then().statusCode(200)
                    .log().all();

        }
    }
    @Nested class ReadAllTestClass{
        @Test
        void testReadAll(){
            String url = Constant.DOMAIN+Constant.APARTMENT_CONTROLLER+Constant.APARTMENT_GET_ALL;

            given()
                    .get(url)
                    .then()
                    .statusCode(200);
        }
    }

    @Nested class deleteTestClass{
        String url = Constant.DOMAIN+Constant.APARTMENT_CONTROLLER+Constant.APARTMENT_DELETE_WITH_ID;
        @Test
        void testDelete(){

        }
    }


}
