package com.housing.authority.Tuples;

import com.housing.authority.Repository.EmployeeRepository;
import com.housing.authority.Repository.ScheduleRepository;
import com.housing.authority.Services.Service;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScheduleTest implements Service {

    private int idForTest;
    private String employeeIdForTest;
    private String date;
    private String from;
    private String to;
    private String description;
    private String registerDate;
    private String lastUpdate;

    @BeforeEach
    void init(){
        idForTest = 1;
        employeeIdForTest = "HAE-158031";

    }

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Order(1)
    @Test
    public void readAllTest() {

    }

    @Override
    @Order(2)
    @Test
    public void readOneTest() {

    }

    @Override
    @Order(3)
    @Test
    public void createTest() {

    }

    @Override
    @Order(4)
    @Test
    public void updateTest() {

    }

    @Override
    @Order(5)
    @Test
    public void deleteTest() {

    }
}