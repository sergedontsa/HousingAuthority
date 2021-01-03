package com.housing.authority.Tuples;

import com.housing.authority.Repository.Subscriber.SubscriberRepository;
import com.housing.authority.Services.Service;
import org.junit.jupiter.api.BeforeEach;
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
class SubscriberTest  implements Service {


    @Autowired
    SubscriberRepository subscriberRepository;

    @BeforeEach
    void init(){

    }

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