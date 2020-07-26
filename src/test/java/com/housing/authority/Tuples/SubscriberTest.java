package com.housing.authority.Tuples;

import com.housing.authority.Repository.Subscriber.SubscriberRepository;
import com.housing.authority.Services.Service;
import com.housing.authority.Tuples.User.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubscriberTest  implements Service {

    private int idForTest;
    private String name;
    private String email;
    private Date registerDate;
    private Date lastUpdate;

    @Autowired
    SubscriberRepository subscriberRepository;

    @BeforeEach
    void init(){
        this.idForTest = 14;
        this.name = "Test";
        this.email = "email@test.com";
        this.registerDate = Date.from(Calendar.getInstance().toInstant());
        this.lastUpdate = Date.from(Calendar.getInstance().toInstant());
    }

    @Override
    @Test
    @Order(1)
    public void readAllTest() {
        List<Subscriber> subscribers = this.subscriberRepository.findAll();
        assertThat(subscribers).size().isGreaterThanOrEqualTo(0);
        for (Subscriber subscriber: subscribers){
            Subscriber sub = this.subscriberRepository.getOne(subscriber.getId());
            assertNotNull(subscriber);
            assertNotNull(sub);
            assertEquals(sub, subscriber);
        }
    }

    @Override
    @Test
    @Order(2)
    public void readOneTest() {
        Subscriber subscriber = this.subscriberRepository.getOne(idForTest);
        assertNotNull(subscriber);
    }

    @Override
    @Test
    @Order(3)
    public void createTest() {
        Subscriber subscriber = new Subscriber(name, email);
        Subscriber saved = this.subscriberRepository.save(subscriber);
        assertNotNull(saved);
        assertEquals(subscriber, saved);
    }

    @Override
    @Test
    @Order(4)
    public void updateTest() {
        Subscriber subscriber = this.subscriberRepository.getOne(this.idForTest);
        assertNotNull(subscriber);
        subscriber.setName(name);
        subscriber.setEmail(email);
        Subscriber updatedSubscriber = this.subscriberRepository.save(subscriber);
        assertNotNull(updatedSubscriber);
        assertEquals(subscriber.getId(), updatedSubscriber.getId());
    }

    @Override
    @Test
    @Order(5)
    public void deleteTest() {
        boolean isPresent = this.subscriberRepository.findById(idForTest).isPresent();
        if (isPresent){
            this.subscriberRepository.delete(this.subscriberRepository.findById(idForTest).get());
            boolean isAbsent = this.subscriberRepository.findById(idForTest).isPresent();
            assertFalse(isAbsent);
        }else {
            fail("Could not locate the entity for test");
        }
    }
}