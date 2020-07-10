package com.housing.authority.Tuples;

import com.housing.authority.Repository.BuildingRepository;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.Services.Service;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BuildingTest implements Service {

    private String idForTest;
    private String idForCreate;

    private int numLevel;
    private int numBedRoom;
    private int numBathRoom;
    private int numLivingRoom;
    private int numKitchen;
    private int numDoor;
    private int numWindows;
    private int numApartment;
    private int numStudio;
    private int numParking;
    private boolean isWithPool;
    private boolean isWithElevator;


    @BeforeEach
    void init(){
        this.idForTest = "IMAH-47091-7";
        this.idForCreate = IDGenerator.BUILDING_ID();

        this.numLevel = 5;
        this.numBedRoom = 50;
        this.numBathRoom = 50;
        this.numLivingRoom = 50;
        this.numKitchen = 50;
        this.numDoor = 50;
        this.numWindows = 50;
        this.numApartment = 50;
        this.numStudio = 50;
        this.numParking = 50;
        this.isWithPool = true;
        this.isWithElevator = true;

    }

    @Autowired
    BuildingRepository buildingRepository;

    @Override
    @Order(1)
    @Test
    public void readAllTest() {
        List<Building> buildings = this.buildingRepository.findAll();
        assertThat(buildings).size().isGreaterThanOrEqualTo(0);

    }

    @Override
    @Order(2)
    @Test
    public void readOneTest() {
        Building building = this.buildingRepository.getOne(this.idForTest);
        assertNotNull(building);
    }

    @Override
    @Order(3)
    @Test
     public void createTest() {
        Building building = new Building();
        building.setBuildingId(this.idForCreate);

        building.setNumLevel(numLevel);
        building.setNumBedRoom(numBedRoom);
        building.setNumBathRoom(numBathRoom);
        building.setNumLivingRoom(numLivingRoom);
        building.setNumKitchen(numKitchen);
        building.setNumDoor(numDoor);
        building.setNumWindows(numWindows);
        building.setNumApartment(numApartment);
        building.setNumStudio(numStudio);
        building.setNumParkingSpace(numParking);
        building.setWithPool(isWithPool);
        building.setWithElevator(isWithElevator);

        assertNotNull(building);
        Building newBuilding = this.buildingRepository.save(building);
        assertNotNull(newBuilding);
    }

    @Override
    @Order(4)
    @Test
    public void updateTest() {
        Building building = this.buildingRepository.getOne(idForTest);

        building.setNumLevel(numLevel);
        building.setNumBedRoom(numBedRoom);
        building.setNumBathRoom(numBathRoom);
        building.setNumLivingRoom(numLivingRoom);
        building.setNumKitchen(numKitchen);
        building.setNumDoor(numDoor);
        building.setNumWindows(numWindows);
        building.setNumApartment(numApartment);
        building.setNumStudio(numStudio);
        building.setNumParkingSpace(numParking);
        building.setWithPool(isWithPool);
        building.setWithElevator(isWithElevator);

        Building updatedBuilding = this.buildingRepository.save(building);
        assertNotNull(updatedBuilding);
    }

    @Override
    @Order(5)
    @Test
    public void deleteTest() {
        boolean isPresent = this.buildingRepository.findById(idForTest).isPresent();
        if (isPresent){
            assertTrue(isPresent);
            this.buildingRepository.delete(this.buildingRepository.findById(idForTest).get());
            boolean isAbsent = this.buildingRepository.findById(idForTest).isPresent();
            assertFalse(isAbsent);
        }else {
            assertFalse(isPresent);
            fail("Could not find the entity to test");
        }
    }
}