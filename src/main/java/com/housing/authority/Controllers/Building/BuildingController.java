package com.housing.authority.Controllers.Building;

import com.housing.authority.Repository.Building.BuildingRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.BuildingModelAssembler;
import com.housing.authority.Tuples.Building.Building;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.BUILDING_CONTROLLER)
public class BuildingController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);
    @Autowired
    private final BuildingRepository buildingRepository;
    @Autowired
    private final BuildingModelAssembler buildingModelAssembler;

    public BuildingController(BuildingRepository buildingRepository, BuildingModelAssembler buildingModelAssembler) {
        this.buildingRepository = buildingRepository;
        this.buildingModelAssembler = buildingModelAssembler;
    }


    @CrossOrigin
    @GetMapping(value = Constant.BUILDING_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<Building>> readAll() {
        List<EntityModel<Building>> buildings = this.buildingRepository.findAll()
                .stream()
                .map(this.buildingModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(buildings, linkTo(methodOn(BuildingController.class).readAll()).withSelfRel());
    }


    @GetMapping(value = Constant.BUILDING_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Building> readOne(@PathVariable String id) {

        if (this.buildingRepository.findById(id).isPresent()) {
            logger.info("[READ ONE] - BUILDING ID: " + id);
            return this.buildingModelAssembler.toModel(this.buildingRepository.findById(id).get());
        }else {
            logger.info("[READ ONE] - ERROR");
            return null;
        }

    }


    @CrossOrigin
    @PostMapping(value = Constant.BUILDING_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Building building) {
        building.setBuildingId(IDGenerator.BUILDING_ID());
        return ResponseEntity.created(this.buildingModelAssembler
                .toModel(this.buildingRepository.save(building))
                .getRequiredLink(IanaLinkRelations.SELF)
                .toUri())
                .body(building);

    }


    @PatchMapping(path = Constant.BUILDING_UPDATE_WITH_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object update(@PathVariable String buildingId, @RequestBody Building building) {
        if (this.buildingRepository.findById(buildingId).isPresent()){
            building.setBuildingId(buildingId);
            this.buildingRepository.save(building);
            
            return HttpStatus.OK;
        }else {
            return HttpStatus.NOT_FOUND;
        }

    }


    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.BUILDING_DELETE_WITH_ID)
    @CrossOrigin
    public void delete(@PathVariable String id) {
        if (this.buildingRepository.findById(id).isPresent()) {
            this.buildingRepository.delete(this.buildingRepository.findById(id).get());

        }
    }
}
