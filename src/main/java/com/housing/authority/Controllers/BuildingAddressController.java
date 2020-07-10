package com.housing.authority.Controllers;


import com.housing.authority.Repository.BuildingAddressRepository;
import com.housing.authority.Repository.BuildingRepository;
import com.housing.authority.Repository.EmployeeRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.BuildingAddressModelAssembler;
import com.housing.authority.Tuples.BuildingAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
@RequiredArgsConstructor
public class BuildingAddressController {

    private final BuildingAddressRepository buildingAddressRepository;
    private final BuildingAddressModelAssembler buildingAddressModelAssembler;
    private final EmployeeRepository employeeRepository;
    private final BuildingRepository buildingRepository;

    @CrossOrigin
    @GetMapping(value = Constant.BUILDING_ADDRESS_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<BuildingAddress>> readAll() {
        List<EntityModel<BuildingAddress>> entityModels = this.buildingAddressRepository.findAll().stream().map(
                this.buildingAddressModelAssembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(entityModels, linkTo(methodOn(BuildingAddressController.class).readAll()).withSelfRel());
    }

    @CrossOrigin
    @GetMapping(value = Constant.BUILDING_ADDRESS_GET_WITH_PROFILE_ID, produces = Constant.PRODUCE)
    public EntityModel<BuildingAddress> readOne(@PathVariable String id) {

        return null;
    }
    @CrossOrigin
    @PostMapping(value = Constant.BUILDING_ADDRESS_SAVE_WITH_PROFILE_ID, consumes = Constant.CONSUMES)
    public ResponseEntity<?> create(@PathVariable String id, @RequestBody BuildingAddress object) {

        return null;
    }
    @CrossOrigin
    @PutMapping(value = Constant.BUILDING_ADDRESS_UPDATE_WITH_PROFILE_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    public Object update(@PathVariable int id, @RequestBody BuildingAddress address) {
        return null;
    }
    @CrossOrigin
    @DeleteMapping(value = Constant.BUILDING_ADDRESS_DELETE_WITH_PROFILE_ID)
    public void delete(@PathVariable String id) {

    }


}
