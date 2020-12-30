package com.housing.authority.Tuples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.housing.authority.HousingAuthorityApplication;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HousingAuthorityApplication.class)
@WebAppConfiguration
public class AbstractTest
{
    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    //protected <T> T mapFromJson(String json, Class<T> cl) throws JsonProcessingException {

    protected JSONObject mapFromJson(String json){
        JSONObject jsonObject = new JSONObject();
        JSONParser jsonParser = new JSONParser();

        try {
            jsonObject = (JSONObject) jsonParser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;

//
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(json, cl);
    }
}
