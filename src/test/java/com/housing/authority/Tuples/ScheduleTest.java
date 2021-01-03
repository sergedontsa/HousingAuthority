package com.housing.authority.Tuples;

import com.housing.authority.Repository.Employee.EmployeeRepository;
import com.housing.authority.Repository.Schedule.ScheduleRepository;
import com.housing.authority.Services.Service;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ScheduleTest implements Service {


    @BeforeEach
    void init(){

    }

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void readAllTest() {

    }

    @Test
    public void readOneTest() {

    }

    @Test
    public void createTest() {

    }

    @Test
    public void updateTest() {

    }

    @Test
    public void deleteTest() {

    }
}