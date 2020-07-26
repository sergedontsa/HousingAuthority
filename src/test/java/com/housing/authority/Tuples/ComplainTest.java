package com.housing.authority.Tuples;

import com.housing.authority.Repository.Complain.ComplainRepository;
import com.housing.authority.Services.Service;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ComplainTest implements Service {
    @Autowired
    ComplainRepository complainRepository;

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