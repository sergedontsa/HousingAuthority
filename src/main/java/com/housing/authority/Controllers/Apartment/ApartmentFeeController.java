package com.housing.authority.Controllers.Apartment;

import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.ApartmentFeeRepository;
import com.housing.authority.Repository.ApartmentRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.ApartmentFeeAssembler;
import com.housing.authority.Tuples.Apartment.ApartmentFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value = Constant.APARTMENT_FEE_CONTROLLER)
public class ApartmentFeeController {
    @Autowired
    private final ApartmentFeeRepository apartmentFeeRepository;
    @Autowired
    private final ApartmentFeeAssembler apartmentFeeAssembler;
    @Autowired
    private final ApartmentRepository apartmentRepository;

    public ApartmentFeeController(ApartmentFeeRepository apartmentFeeRepository, ApartmentFeeAssembler apartmentFeeAssembler, ApartmentRepository apartmentRepository) {
        this.apartmentFeeRepository = apartmentFeeRepository;
        this.apartmentFeeAssembler = apartmentFeeAssembler;
        this.apartmentRepository = apartmentRepository;
    }

    @GetMapping(value = Constant.APARTMENT_FEE_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<ApartmentFee>> readAll(){
        List<EntityModel<ApartmentFee>> apartmentFee = this.apartmentFeeRepository
                .findAll()
                .stream()
                .map(this.apartmentFeeAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(apartmentFee,
                linkTo(methodOn(ApartmentFeeController.class)
                .readAll())
        .withSelfRel());
    }

    @GetMapping(value = Constant.APARTMENT_FEE_GET_WIT_APARTMENT_ID)
    @CrossOrigin
    public EntityModel<ApartmentFee> readOne(@PathVariable String apartmentId){

        ApartmentFee apartmentFee = this.apartmentFeeRepository.findById(apartmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Resource Id: " + apartmentId + " could not be found"));
        return this.apartmentFeeAssembler.toModel(apartmentFee);
    }

    @PostMapping(value = Constant.APARTMENT_FEE_SAVE_WITH_APARTMENT_ID, consumes = Constant.CONSUMES)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable String apartmentId, @RequestBody ApartmentFee apartmentFee){
        if (!this.apartmentRepository.existsById(apartmentId)){
            throw new ResourceNotFoundException("Resource Id: " + apartmentId + " could not be found");
        }
        apartmentFee.setApartmentId(apartmentId);
        EntityModel<ApartmentFee> entityModel = this.apartmentFeeAssembler.toModel(this.apartmentFeeRepository.save(apartmentFee));

        this.apartmentRepository.setApartmentFeeId(apartmentId, apartmentId);
        return ResponseEntity.created(this.apartmentFeeAssembler.toModel(this.apartmentFeeRepository.save(apartmentFee))
        .getRequiredLink(IanaLinkRelations.SELF)
        .toUri()).body(entityModel);
    }

    @PatchMapping(path = Constant.APARTMENT_FEE_UPDATE_WITH_APARTMENT_ID, consumes = Constant.CONSUMES)
    @CrossOrigin
    public ResponseEntity<?> update(@PathVariable String apartmentId){
        return null;
    }
}
