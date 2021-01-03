package com.housing.authority.Tuples;

import com.housing.authority.Repository.Employee.EmployeeRepository;
import com.housing.authority.Services.Service;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeTest {


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