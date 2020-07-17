package com.housing.authority.Controllers.Building;


import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.BuildingAddressRepository;
import com.housing.authority.Repository.BuildingRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.BuildingAddressModelAssembler;
import com.housing.authority.Tuples.Building.BuildingAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.BUILDING_ADDRESS_CONTROLLER)
public class BuildingAddressController {

    @Autowired
    private final BuildingAddressRepository buildingAddressRepository;
    @Autowired
    private final BuildingAddressModelAssembler buildingAddressModelAssembler;
    @Autowired
    private final BuildingRepository buildingRepository;

    public BuildingAddressController(BuildingAddressRepository buildingAddressRepository, BuildingAddressModelAssembler buildingAddressModelAssembler, BuildingRepository buildingRepository) {
        this.buildingAddressRepository = buildingAddressRepository;
        this.buildingAddressModelAssembler = buildingAddressModelAssembler;
        this.buildingRepository = buildingRepository;
    }

    @CrossOrigin
    @GetMapping(value = Constant.BUILDING_ADDRESS_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<BuildingAddress>> readAll() {
        List<EntityModel<BuildingAddress>> entityModels = this.buildingAddressRepository
                .findAll()
                .stream()
                .map(this.buildingAddressModelAssembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(entityModels, linkTo(methodOn(BuildingAddressController.class)
                .readAll())
                .withSelfRel());
    }

    @CrossOrigin
    @GetMapping(value = Constant.BUILDING_ADDRESS_GET_WITH_BUILDING_ID, produces = Constant.PRODUCE)
    public EntityModel<BuildingAddress> readOne(@PathVariable String buildingId) {

        if (isBuildingExist(buildingId)) {
            return this.buildingAddressModelAssembler.toModel(this.buildingAddressRepository.getOne(buildingId));
        }else {
            throw new ResourceNotFoundException("Resource Id: " + buildingId + " could not be found");
        }


    }
    @CrossOrigin
    @PostMapping(value = Constant.BUILDING_ADDRESS_SAVE_WITH_BUILDING_ID, consumes = Constant.CONSUMES)
    public ResponseEntity<?> create(@PathVariable String buildingId, @RequestBody BuildingAddress object) {
        if (isBuildingExist(buildingId)){
            return this.buildingRepository.findById(buildingId).map(building -> {
                object.setBuildingid(building.getBuildingId());
                EntityModel<BuildingAddress> entityModel = buildingAddressModelAssembler.toModel(this.buildingAddressRepository.save(object));
                return ResponseEntity.created(buildingAddressModelAssembler.toModel(this.buildingAddressRepository.save(object)).getRequiredLink(IanaLinkRelations.SELF)
                .toUri()).body(entityModel);
            }).orElseThrow(()-> new ResourceNotFoundException("Resource Id: " + buildingId + " could not be found"));
        }

        return null;
    }
    @CrossOrigin
    @PutMapping(value = Constant.BUILDING_ADDRESS_UPDATE_WITH_BUILDING_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    public Object update(@PathVariable int buildingId, @RequestBody BuildingAddress address) {
        return null;
    }
    @CrossOrigin
    @DeleteMapping(value = Constant.BUILDING_ADDRESS_DELETE_WITH_BUILDING_ID)
    public void delete(@PathVariable String buildingId) {

    }
    private boolean isBuildingExist(String buildingId){
        if (!this.buildingRepository.existsById(buildingId)){
            throw new ResourceNotFoundException("Resource Id: " + buildingId + " could not be found");
        }
        return true;
    }


}
