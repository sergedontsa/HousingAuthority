package com.housing.authority.Controllers.Apartment;

import com.housing.authority.Enum.ApartmentStatus;
import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.Apartment.ApartmentDimensionRepository;
import com.housing.authority.Repository.Apartment.ApartmentFeeRepository;
import com.housing.authority.Repository.Apartment.ApartmentRepository;
import com.housing.authority.Repository.Building.BuildingRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.Apartment.ApartmentModelAssembler;
import com.housing.authority.Tuples.Apartment.Apartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
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

/**
 * Note: still check delete request
 * the request work but does not delete the entity
 */

@RestController
@RequestMapping(value = Constant.APARTMENT_CONTROLLER)
//@PreAuthorize("isAuthenticated()")
public class ApartmentController{
    private static final Logger logger = LoggerFactory.getLogger(ApartmentController.class);
    @Autowired
    private final ApartmentRepository apartmentTupleRepository;
    @Autowired
    private final ApartmentModelAssembler apartmentModelAssembler;
    @Autowired
    private final BuildingRepository buildingRepository;
    @Autowired
    private final ApartmentDimensionRepository apartmentDimensionRepository;
    @Autowired
    private final ApartmentFeeRepository apartmentFeeRepository;

    public ApartmentController(ApartmentRepository apartmentTupleRepository, ApartmentModelAssembler apartmentModelAssembler, BuildingRepository buildingRepository, ApartmentDimensionRepository apartmentDimensionRepository, ApartmentFeeRepository apartmentFeeRepository) {
        this.apartmentTupleRepository = apartmentTupleRepository;
        this.apartmentModelAssembler = apartmentModelAssembler;
        this.buildingRepository = buildingRepository;
        this.apartmentDimensionRepository = apartmentDimensionRepository;
        this.apartmentFeeRepository = apartmentFeeRepository;
    }

    @GetMapping(value = Constant.APARTMENT_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    //@PreAuthorize("permitAll()")
    public CollectionModel<EntityModel<Apartment>> readAll(){
        logger.info("Read All: " + Constant.APARTMENT_GET_ALL);
        List<EntityModel<Apartment>> apartments = this.apartmentTupleRepository
                .findAll()
                .stream()
                .map(this.apartmentModelAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(apartments, linkTo(methodOn(ApartmentController.class)
                .readAll())
                .withSelfRel());
    }


    @GetMapping(value = Constant.APARTMENT_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Apartment> readOne(@PathVariable String id){
        logger.info("Read One: " + id);
        if (this.apartmentTupleRepository.findById(id).isPresent()) {
            return this.apartmentModelAssembler.toModel(this.apartmentTupleRepository.findById(id).get());
        }else {
            return null;
        }
    }

    @PostMapping(value = Constant.APARTMENT_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<?> create(@PathVariable String buildingId,@RequestBody Apartment apartment){
        logger.info("[Create]: Building Id" + buildingId);
        logger.info("[Object]: " + apartment);
        if (!this.buildingRepository.existsById(buildingId)){
            throw new ResourceNotFoundException("BUILDING ID: " + buildingId+ " could not be found");
        }else {
            apartment.setApartmentID(IDGenerator.APARTMENT_ID());
            apartment.setStatus(String.valueOf(ApartmentStatus.Available));

            apartment.setBuilding(this.buildingRepository.getOne(buildingId));
            EntityModel<Apartment> entityModel = this.apartmentModelAssembler
                    .toModel(this.apartmentTupleRepository.save(apartment));

            return ResponseEntity.created(apartmentModelAssembler.toModel(this.apartmentTupleRepository.save(apartment))
                    .getRequiredLink(IanaLinkRelations.SELF)
                    .toUri()).body(entityModel);
        }
    }

    @DeleteMapping(value = Constant.APARTMENT_DELETE_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable String apartmentId){
        logger.warn("[DELETE]: ID" + apartmentId);
        if (!apartmentTupleRepository.existsById(apartmentId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.apartmentTupleRepository.deleteById(apartmentId);
        this.apartmentDimensionRepository.deleteById(apartmentId);
        this.apartmentFeeRepository.deleteById(apartmentId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PatchMapping(path = Constant.APARTMENT_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<?> update(@PathVariable String id, @PathVariable String buildingId, @RequestBody Apartment apartment){
        logger.info("[UPDATE]: Apartment ID" + id );
        logger.info("[UPDATE]: Building ID" + buildingId );
        logger.info("[UPDATE]: Apartment" + apartment );
        if(!this.apartmentTupleRepository.existsById(id) || !this.buildingRepository.existsById(buildingId)){
            logger.error("Resource Id: " + id+ " could not be found");
            throw new ResourceNotFoundException("Resource Id: " + id+ " could not be found");
        }else {
            apartment.setBuilding(this.buildingRepository.getOne(buildingId));
            apartment.setApartmentFee(this.apartmentFeeRepository.getOne(id));
            apartment.setApartmentDimension(this.apartmentDimensionRepository.getOne(id));
            apartment.setApartmentID(id);

            this.apartmentTupleRepository.save(apartment);
            logger.info("[UPDATE]: Status" + HttpStatus.OK.toString());
            return new ResponseEntity<Apartment>(HttpStatus.OK);
        }

    }
}
