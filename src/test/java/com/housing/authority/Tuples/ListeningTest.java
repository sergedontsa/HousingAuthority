package com.housing.authority.Tuples;

import com.housing.authority.Repository.Apartment.ApartmentRepository;
import com.housing.authority.Repository.Building.BuildingRepository;
import com.housing.authority.Repository.Listenening.ListeningRepository;
import com.housing.authority.Services.Service;
import com.housing.authority.Tuples.Apartment.Apartment;
import com.housing.authority.Tuples.Building.Building;
import com.housing.authority.Tuples.Listening.Listening;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ListeningTest implements Service {
    private String apartmentIDForTest;
    private String buildingIDForTest;
    @Autowired
    ApartmentRepository apartmentRepository;
    @Autowired
    ListeningRepository listeningRepository;
    @Autowired
    BuildingRepository buildingRepository;

    @BeforeEach
    void init(){
        this.apartmentIDForTest = "APT-8840511-3";
        this.buildingIDForTest = "IMAH-47091-7";
    }


    @Override
    @Test
    @Order(1)
    public void readAllTest() {
        List<Listening> listenings = this.listeningRepository.findAll();
        assertThat(listenings).size().isGreaterThanOrEqualTo(0);
        listenings = null;      //garbage collection
        assertNull(listenings);
    }

    @Override
    @Test
    @Order(2)
    public void readOneTest() {
        Building building = this.buildingRepository.getOne(buildingIDForTest);
        assertNotNull(building);
        Apartment apartment = this.apartmentRepository.getOne(apartmentIDForTest);
        assertNotNull(apartment);
        Listening listening = this.listeningRepository.getOne(apartmentIDForTest);
        assertNotNull(listening);
        assertEquals(listening.getApartmentId(), apartment.getApartmentID());
        assertEquals(building.getBuildingId(), listening.getBuildingid());
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