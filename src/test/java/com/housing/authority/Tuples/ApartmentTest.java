package com.housing.authority.Tuples;

import com.housing.authority.Repository.Apartment.ApartmentRepository;
import com.housing.authority.Repository.Building.BuildingRepository;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.Services.Service;
import com.housing.authority.Tuples.Apartment.Apartment;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


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

    private int numBath;
    private int numBedRoom;
    private int numCloset;
    private int numKitchen;
    private int numLivingRoom;
    private int numWindows;
    private boolean isWithBath;
    private boolean isWithWaterBoiler;
    private String status;


    @BeforeEach
    void init(){
        this.idForCreate= IDGenerator.APARTMENT_ID();
        this.idForTest = "APT-8845623-3";

        this.numBath = 10;
        this.numBedRoom = 10;
        this.numCloset = 10;
        this.numKitchen = 10;
        this.numLivingRoom = 10;
        this.numWindows = 10;
        this.isWithBath = true;
        this.isWithWaterBoiler = true;
        this.status = "Avail Test";

    }
    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    BuildingRepository buildingRepository;


    @Override
    @Test
    @Order(1)
    public void readAllTest() {

    }

    @Override
    @Test
    @Order(2)
    public void readOneTest() {
        Apartment apartment = this.apartmentRepository.getOne(this.idForTest);
        assertNotNull(apartment);

    }

    @Override
    @Test
    @Order(3)
    public void createTest() {
        Apartment apartment = new Apartment();
        apartment.setApartmentID(this.idForCreate);

        apartment.setNumBathRoom(this.numBath);
        apartment.setNumBedRoom(this.numBedRoom);
        apartment.setNumCloset(this.numCloset);
        apartment.setNumKitchen(this.numKitchen);
        apartment.setNumLivingRoom(this.numLivingRoom);
        apartment.setNumWindows(this.numWindows);
        apartment.setWithBath(this.isWithBath);
        apartment.setWithWaterBoiler(this.isWithWaterBoiler);
        apartment.setStatus(this.status);


        Apartment newApartment = this.apartmentRepository.save(apartment);
        assertNotNull(newApartment);
    }

    @Override
    @Test
    @Order(4)
    public void updateTest() {
        Apartment apartment = this.apartmentRepository.getOne(this.idForTest);
        assertNotNull(apartment);

        apartment.setNumBathRoom(this.numBath);
        apartment.setNumBedRoom(this.numBedRoom);
        apartment.setNumCloset(this.numCloset);
        apartment.setNumKitchen(this.numKitchen);
        apartment.setNumLivingRoom(this.numLivingRoom);
        apartment.setNumWindows(this.numWindows);
        apartment.setWithBath(this.isWithBath);
        apartment.setWithWaterBoiler(this.isWithWaterBoiler);
        apartment.setStatus(this.status);

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