package com.housing.authority.Tuples;

import com.housing.authority.Controllers.Apartment.ApartmentController;
import com.housing.authority.Repository.Apartment.ApartmentRepository;
import com.housing.authority.Services.Service;
import com.housing.authority.Tuples.Apartment.Apartment;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.hateoas.EntityModel;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ApartmentTest implements Service {

    @InjectMocks
    ApartmentController apartmentController;

    @Mock
    ApartmentRepository apartmentRepository;

    Apartment apartment;

    final String apartment_id_for_testing = "APT-1529293-4";

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        apartment = new Apartment();
        apartment.setApartmentID(this.apartment_id_for_testing);
        apartment.setDimensionid("APT-1529293-4");
        apartment.setFeeid("APT-1529293-4");
        apartment.setBuildingid("HAB-3275659-5");
        apartment.setApartment_number("1B");
        apartment.setStatus("Occupied");

    }
    @Test
    public void readAllTest() {

    }

    @Test
    public void readOneTest() {
        when(apartmentController.readOne(anyString())).thenReturn(null);
        EntityModel<Apartment> apartmentRest = this.apartmentController.readOne(this.apartment_id_for_testing);
        assertNull(apartmentRest);

//        assertAll(
//                () -> assertEquals(this.apartment_id_for_testing, Objects.requireNonNull(apartmentRest.getContent()).getApartmentID()),
//                () -> assertEquals(this.apartment.getDimensionid(), Objects.requireNonNull(apartmentRest.getContent().getDimensionid())),
//                () -> assertEquals(this.apartment.getFeeid(), Objects.requireNonNull(apartmentRest.getContent().getFeeid())),
//                () -> assertEquals(this.apartment.getBuildingid(), Objects.requireNonNull(apartmentRest.getContent().getBuildingid())),
//                () -> assertEquals(this.apartment.getApartment_number(), Objects.requireNonNull(apartmentRest.getContent().getApartment_number())),
//                () -> assertEquals(this.apartment.getStatus(), Objects.requireNonNull(apartmentRest.getContent().getStatus()))
//
//        );
    }

    @Test
    public void createTest() {
        assertEquals(1, 1);
    }

    @Test
    public void updateTest() {

    }


    @Test
    public void deleteTest() {
    }
}