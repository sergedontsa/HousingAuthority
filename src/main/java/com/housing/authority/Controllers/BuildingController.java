package com.housing.authority.Controllers;

import com.housing.authority.Repository.BuildingRepository;
import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.BuildingModelAssembler;
import com.housing.authority.Tuples.Building;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.BUILDING_CONTROLLER)
@RequiredArgsConstructor
public class BuildingController implements ServiceController<Building> {
    private final BuildingRepository buildingRepository;
    private final BuildingModelAssembler buildingModelAssembler;

    @Override
    @CrossOrigin
    @GetMapping(value = Constant.BUILDING_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<Building>> readAll() {
        List<EntityModel<Building>> buildings = this.buildingRepository.findAll().stream().map(this.buildingModelAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(buildings, linkTo(methodOn(BuildingController.class).readAll()).withSelfRel());
    }

    @Override
    @GetMapping(value = Constant.BUILDING_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Building> readOne(@PathVariable String id) {
        if (this.buildingRepository.findById(id).isPresent()) {
            return this.buildingModelAssembler.toModel(this.buildingRepository.findById(id).get());
        }else {
            return null;
        }
    }

    @Override
    public EntityModel<Building> readOne(int id) {
        return null;
    }

    @Override
    @PostMapping(value = Constant.BUILDING_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Building object) {
        object.setBuildingId(IDGenerator.BUILDING_ID());
        return ResponseEntity.created(this.buildingModelAssembler.toModel(this.buildingRepository.save(object))
                .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(object);

    }

    @Override
    @PatchMapping(path = Constant.BUILDING_UPDATE_WITH_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object update(@PathVariable String id, @RequestBody Building building) {
        if (this.buildingRepository.findById(id).isPresent()){
            Building existingBuilding = this.buildingRepository.findById(id).get();
            existingBuilding.setBuildingNumber(building.getBuildingNumber());
            existingBuilding.setCity(building.getCity());
            existingBuilding.setProvince(building.getProvince());
            existingBuilding.setZipCode(building.getZipCode());
            existingBuilding.setCountry(building.getCountry());
            existingBuilding.setNumLevel(building.getNumLevel());
            existingBuilding.setNumBedRoom(building.getNumBedRoom());
            existingBuilding.setNumBathRoom(building.getNumBathRoom());
            existingBuilding.setNumLivingRoom(building.getNumLivingRoom());
            existingBuilding.setNumKitchen(building.getNumKitchen());
            existingBuilding.setNumDoor(building.getNumDoor());
            existingBuilding.setNumWindows(building.getNumWindows());
            existingBuilding.setNumApartment(building.getNumApartment());
            existingBuilding.setNumStudio(building.getNumStudio());
            existingBuilding.setNumParkingSpace(building.getNumParkingSpace());
            existingBuilding.setWithElevator(building.isWithElevator());
            existingBuilding.setWithPool(building.isWithPool());
            this.buildingRepository.save(existingBuilding);
            return HttpStatus.OK;
        }else {
            return HttpStatus.NOT_FOUND;
        }

    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.BUILDING_DELETE_WITH_ID)
    @CrossOrigin
    public void delete(@PathVariable String id) {
        if (this.buildingRepository.findById(id).isPresent()) {
            this.buildingRepository.delete(this.buildingRepository.findById(id).get());

        }
    }
}
