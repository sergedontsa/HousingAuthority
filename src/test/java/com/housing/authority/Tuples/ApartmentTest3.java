package com.housing.authority.Tuples;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.housing.authority.Tuples.Apartment.Apartment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.testng.AssertJUnit.assertEquals;

public class ApartmentTest3 {

    @Test
    void testReadOne() throws ParseException {
        String url = "http://localhost:1000/housing/authority/v1/apartment/get/APT-1529293-4";
        Response response = RestAssured.get(url);
        int code = response.getStatusCode();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();

        jsonObject = (JSONObject) jsonParser.parse(response.body().asString());

        JSONObject finalJsonObject = jsonObject;
        assertAll(
                () -> assertEquals(code, 200),
                () -> assertEquals("APT-1529293-4", finalJsonObject.get("apartmentID"))
        );


    }

    @Test
    void testReadAll(){
        String url = "http://localhost:1000/housing/authority/v1/apartment/get/all";
        given()
                .get(url)
        .then()
                .statusCode(200);

    }

    @Test
    void testCreate() throws JsonProcessingException, ParseException {
        String url = "http://localhost:1000/housing/authority/v1/apartment/HAB49498083/save";
        Apartment apartment = new Apartment();
        apartment.setApartment_number("1A");
        apartment.setBuildingid("");
        apartment.setNumBathRoom(2);
        apartment.setNumBedRoom(2);
        apartment.setNumCloset(2);
        apartment.setNumKitchen(1);
        apartment.setNumLivingRoom(1);
        apartment.setNumWindows(6);
        apartment.setStatus("Available");

        ObjectMapper objectMapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(objectMapper.writeValueAsString(apartment));


        System.out.println(jsonObject.toJSONString());


        given().
                body(jsonObject)
                .when().
                post(url).
                then().statusCode(415);




    }
}
