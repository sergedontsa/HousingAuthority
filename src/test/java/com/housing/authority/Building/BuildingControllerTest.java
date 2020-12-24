package com.housing.authority.Building;

import com.housing.authority.Repository.Building.BuildingRepository;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.Tuples.Building.Building;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BuildingControllerTest {

    @Autowired
    BuildingRepository buildingRepository;

    @Test
    public void testSave(){
        Building building = new Building();
        building.setBuildingId(IDGenerator.BUILDING_ID());
        Building saved = buildingRepository.save(building);

        assertNotNull(saved.getBuildingId());

    }

}
