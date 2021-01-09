package com.housing.authority.Tuples;

import com.housing.authority.Resources.Constant;
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

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BuildingTest {

    private Map<String, Object> sample_building;
    private String building_id_for_test_read;


    @BeforeAll
    public void init(){

        this.sample_building = new HashMap<>();
        this.building_id_for_test_read = "HAB-3275659-5";
        sample_building.put("numlevel",4);
        sample_building.put("numbedroom", 64);
        sample_building.put("numbathroom", 16);
        sample_building.put("numlivingroom", 16);
        sample_building.put("numkitchen", 16);
        sample_building.put("numdoor", 150);
        sample_building.put("numwindows", 177);
        sample_building.put("numapartment", 16);
        sample_building.put("numstudio", 0);
        sample_building.put("numparkingspace", 20);
        sample_building.put("iswithpool", true);
        sample_building.put("iswithelevator", true);


    }


    @Nested
    class saveTestClass {
        @Test
        @DisplayName(">>Save One Test")
        @Order(1)
        void saveTest() {
            String url_save = Constant.DOMAIN+Constant.BUILDING_CONTROLLER+Constant.BUILDING_SAVE;
            given().contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(BuildingTest.this.sample_building)
                    .when().post(url_save)
                    .then().statusCode(201)
                    .log().all();
        }
    }

    @Nested
    class readOneTestClass{
        @Test
        @DisplayName(">>Read One Test")
        @Order(2)
        void testReadOne(){
            String url_read = Constant.DOMAIN+Constant.BUILDING_CONTROLLER+Constant.BUILDING_GET_WITH_ID;
            given().pathParam("id", BuildingTest.this.building_id_for_test_read)
                    .when().get(url_read)
                    .then().statusCode(200)
                    .log().body();
        }

    }
    @Nested class ReadAllTestClass{
        @Test
        void testReadAll(){
            String url = Constant.DOMAIN+Constant.BUILDING_CONTROLLER+Constant.BUILDING_GET_ALL;
            given().get(url).then().statusCode(200);
        }
    }
    @Nested class DeleteTestClass{
        @Test
        void testDelete(){

        }
    }
}