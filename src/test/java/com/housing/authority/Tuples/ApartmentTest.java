package com.housing.authority.Tuples;

import com.housing.authority.Repository.ApartmentRepository;
import com.housing.authority.Repository.BuildingRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.Services.Service;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApartmentTest implements Service {

    private String idForCreate;
    private String idForTest;
    private String buildingIdForTest;
    private int numBath;
    private int numBedRoom;
    private int numCloset;
    private int numKitchen;
    private int numLivingRoom;
    private int numWindows;
    private boolean isWithBath;
    private boolean isWithWaterBoiler;
    private String status;
    private String registerDate;
    private String lastUpdate;

    @BeforeEach
    void init(){
        this.idForCreate= IDGenerator.APARTMENT_ID();
        this.idForTest = "APT-8845623-3";
        this.buildingIdForTest = "IMAH-47091-7";
        this.numBath = 10;
        this.numBedRoom = 10;
        this.numCloset = 10;
        this.numKitchen = 10;
        this.numLivingRoom = 10;
        this.numWindows = 10;
        this.isWithBath = true;
        this.isWithWaterBoiler = true;
        this.status = "Avail Test";
        this.registerDate = Constant.getCurrentDateAsString();
        this.lastUpdate = Constant.getCurrentDateAsString();
    }
    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    BuildingRepository buildingRepository;


    @Override
    @Test
    @Order(1)
    public void readAllTest() {
        List<Apartment> apartments = this.apartmentRepository.findAll();
        assertThat(apartments).size().isGreaterThanOrEqualTo(0);
        for (Apartment apartment: apartments){
            Building building = this.buildingRepository.getOne(apartment.getBuildingid());
            assertNotNull(building);
            assertEquals(apartment.getBuildingid(), building.getBuildingId());
        }
    }

    @Override
    @Test
    @Order(2)
    public void readOneTest() {
        Apartment apartment = this.apartmentRepository.getOne(this.idForTest);
        assertNotNull(apartment);
        assertEquals(apartment.getBuildingid(), this.buildingRepository.getOne(this.buildingIdForTest).getBuildingId());
    }

    @Override
    @Test
    @Order(3)
    public void createTest() {
        Apartment apartment = new Apartment();
        apartment.setApartmentID(this.idForCreate);
        apartment.setBuildingid(this.buildingIdForTest);
        apartment.setNumBathRoom(this.numBath);
        apartment.setNumBedRoom(this.numBedRoom);
        apartment.setNumCloset(this.numCloset);
        apartment.setNumKitchen(this.numKitchen);
        apartment.setNumLivingRoom(this.numLivingRoom);
        apartment.setNumWindows(this.numWindows);
        apartment.setWithBath(this.isWithBath);
        apartment.setWithWaterBoiler(this.isWithWaterBoiler);
        apartment.setStatus(this.status);
        apartment.setRegisterdate(this.registerDate);
        apartment.setLastupdate(this.lastUpdate);

        Apartment newApartment = this.apartmentRepository.save(apartment);
        assertNotNull(newApartment);
    }

    @Override
    @Test
    @Order(4)
    public void updateTest() {
        Apartment apartment = this.apartmentRepository.getOne(this.idForTest);
        assertNotNull(apartment);
        assertEquals(this.buildingIdForTest, apartment.getBuildingid());
        apartment.setApartmentID(this.idForCreate);
        apartment.setBuildingid(this.buildingIdForTest);
        apartment.setNumBathRoom(this.numBath);
        apartment.setNumBedRoom(this.numBedRoom);
        apartment.setNumCloset(this.numCloset);
        apartment.setNumKitchen(this.numKitchen);
        apartment.setNumLivingRoom(this.numLivingRoom);
        apartment.setNumWindows(this.numWindows);
        apartment.setWithBath(this.isWithBath);
        apartment.setWithWaterBoiler(this.isWithWaterBoiler);
        apartment.setStatus(this.status);
        apartment.setRegisterdate(this.registerDate);
        apartment.setLastupdate(this.lastUpdate);
        Apartment updatedApartment = this.apartmentRepository.save(apartment);
        assertNotNull(updatedApartment);
    }

    @Override
    @Test
    @Order(5)
    public void deleteTest() {
       boolean isPresent = this.apartmentRepository.findById(this.idForTest).isPresent();
       if (isPresent){
           assertTrue(isPresent);
           this.apartmentRepository.delete(this.apartmentRepository.findById(this.idForTest).get());
           boolean isAbsent = this.apartmentRepository.findById(this.idForTest).isPresent();
           assertFalse(isAbsent);
       }else{
           fail("Could not locate the entity for test");
       }
    }
}