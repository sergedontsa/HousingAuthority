package com.housing.authority;

import com.housing.authority.Controllers.HousingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class HousingAuthorityApplicationTests {

    @Autowired
    private HousingController housingController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {

    }
    @Test
    void testAdd(){
        assertEquals(10, housingController.add(5, 5));
    }



}
