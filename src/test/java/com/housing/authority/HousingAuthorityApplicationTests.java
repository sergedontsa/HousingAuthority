package com.housing.authority;

import com.housing.authority.Controllers.HousingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HousingAuthorityApplicationTests {
    ToDemoTest toDemoTest;
    @Autowired
    private HousingController housingController;

    @BeforeEach
    public void init(){
        System.out.println("Init test ");
        toDemoTest = new ToDemoTest();
    }
    @Test
    void contextLoads() {
        assertThat(housingController).isNotNull();
    }
    @Test
    void testAdd(){
        assertEquals(0, toDemoTest.add(0, 0));
    }

}
