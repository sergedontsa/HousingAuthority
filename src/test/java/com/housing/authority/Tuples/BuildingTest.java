package com.housing.authority.Tuples;

import com.housing.authority.Repository.Building.BuildingRepository;
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

class BuildingTest implements Service {



    @BeforeEach
    void init(){


    }

    @Autowired
    BuildingRepository buildingRepository;

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