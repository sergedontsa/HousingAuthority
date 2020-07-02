package com.housing.authority.Tuples;

import com.housing.authority.Repository.SubscriberRepository;
import com.housing.authority.Services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubscriberTest  implements Service {

    @Autowired
    SubscriberRepository subscriberRepository;


    @BeforeEach
    void init(){

    }

    @Override
    @Test
    @Order(1)
    public void readAllTest() {

    }

    @Override
    @Test
    @Order(2)
    public void readOneTest() {

    }

    @Override
    @Test
    @Order(3)
    public void createTest() {

    }

    @Override
    @Test
    @Order(4)
    public void updateTest() {

    }

    @Override
    @Test
    @Order(5)
    public void deleteTest() {

    }
}