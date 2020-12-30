package com.housing.authority.Tuples;

import com.housing.authority.Tuples.Apartment.Apartment;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ApartmentTest2 extends AbstractTest{


    @Autowired
    WebApplicationContext webApplicationContext;

    @Override
    @Before
    public void setUp(){
        super.setUp();
    }

    @Test
    public void readAllTest() throws Exception{
        String url = "http://localhost:1000/housing/authority/v1/apartment/get/all";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(url)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = result.getResponse().getStatus();

        assertEquals(200, status);

        JSONObject jsonObject = super.mapFromJson(result.getResponse().getContentAsString());
        assertTrue(jsonObject.size() > 0);


    }

    @Test
    public void readOneTest() throws Exception {
        String url = "http://localhost:1000/housing/authority/v1/apartment/get/APT-1529293-4";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(url)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(200, result.getResponse().getStatus());

        JSONObject jsonObject = super.mapFromJson(result.getResponse().getContentAsString());
        assertTrue(jsonObject.size()>0);

    }

    @Test
    public void readOneThrowTest() throws Exception {
        String url = "http://localhost:1000/housing/authority/v1/apartment/get/APT-1529293";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(url)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertEquals(404, result.getResponse().getStatus());

    }

    //https://www.tutorialspoint.com/spring_boot/spring_boot_rest_controller_unit_test.htm
    @Test
    public void createTest() throws Exception {
        String url = "http://localhost:1000//housing/authority/v1/apartment/HAB-3275659-5/save";
        Apartment apartment = new Apartment();
        apartment.setBuildingid("HAB-3275659-5");
        apartment.setApartment_number("1A");
        apartment.setNumBathRoom(1);
        apartment.setNumBedRoom(2);
        apartment.setNumCloset(3);
        apartment.setNumKitchen(1);
        apartment.setNumLivingRoom(1);
        apartment.setNumWindows(7);
        apartment.setStatus("Available");

        String content = super.mapToJson(apartment);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(url)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .content(content)).andReturn();

        assertEquals(201, result.getResponse().getStatus());

    }


}
