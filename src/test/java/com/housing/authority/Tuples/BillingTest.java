package com.housing.authority.Tuples;

import com.housing.authority.Services.Service;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class BillingTest implements Service {


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