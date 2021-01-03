package com.housing.authority.Tuples;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BillingTest{

    private Map<String, Object> sample_billing;
    private String billing_id_for_test;



    @Before
    public void init(){

    }

    @Nested
    class saveTestClass {
        @Test
        @DisplayName(">>Save One Test")
        @Order(1)
        void saveTest() {

        }
    }

    @Nested
    class readOneTestClass{
        @Test
        @DisplayName(">>Read One Test")
        @Order(2)
        void testReadOne(){

        }

    }
    @Nested class ReadAllTestClass{
        @Test
        void testReadAll(){

        }
    }
    @Nested class DeleteTestClass{
        @Test
        void testDelete(){

        }
    }


}