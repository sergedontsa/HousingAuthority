package com.housing.authority.Controllers;

import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.ApartmentDimensionRepository;
import com.housing.authority.Repository.ApartmentRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.ApartmentDimensionAssembler;
import com.housing.authority.Tuples.ApartmentDimension;
import lombok.RequiredArgsConstructor;
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

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constant.APARTMENT_DIMENSION_CONTROLLER)
public class ApartmentDimensionController {

    @Autowired
    private final ApartmentDimensionRepository apartmentDimensionRepository;
    @Autowired
    private final ApartmentDimensionAssembler apartmentDimensionAssembler;
    @Autowired
    private final ApartmentRepository apartmentRepository;

    @GetMapping(value = Constant.APARTMENT_DIMENSION_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<ApartmentDimension>> readAll(){
        List<EntityModel<ApartmentDimension>> dimension = this.apartmentDimensionRepository
                .findAll()
                .stream()
                .map(this.apartmentDimensionAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(dimension,
                linkTo(methodOn(ApartmentDimensionController.class)
                .readAll())
        .withSelfRel());
    }

    @GetMapping(value = Constant.APARTMENT_DIMENSION_GET_WITH_APARTMENT_ID)
    @CrossOrigin
    public EntityModel<ApartmentDimension> readOne(@PathVariable String apartmentId){
        ApartmentDimension apartmentDimension = this.apartmentDimensionRepository.findById(apartmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Resource Id: " + apartmentId + " could not be found"));

        return this.apartmentDimensionAssembler.toModel(apartmentDimension);
    }

    @PostMapping(value = Constant.APARTMENT_DIMENSION_SAVE_WITH_APARTMENT_ID, consumes = Constant.CONSUMES)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable String apartmentId, @RequestBody ApartmentDimension dimension){
        if (!this.apartmentRepository.existsById(apartmentId)){
            throw new ResourceNotFoundException("Resource Id: " + apartmentId + " could not be found");
        }
        dimension.setApartmentId(apartmentId);
        EntityModel<ApartmentDimension> entityModel = this.apartmentDimensionAssembler.toModel(this.apartmentDimensionRepository.save(dimension));
        this.apartmentRepository.setApartmentDimensionId(apartmentId, apartmentId);
        return ResponseEntity.created(this.apartmentDimensionAssembler.toModel(this.apartmentDimensionRepository.save(dimension))
        .getRequiredLink(IanaLinkRelations.SELF)
        .toUri()).body(entityModel);
    }
    @DeleteMapping(value = Constant.APARTMENT_DIMENSION_DELETE_WITH_APARTMENT_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable String apartmentId){
        return  null;
    }
    @PatchMapping(path = Constant.APARTMENT_DIMENSION_UPDATE_WITH_APARTMENT_ID, consumes = Constant.CONSUMES)
    @CrossOrigin
    public ResponseEntity<?> update(@PathVariable String apartmentId){
        return null;
    }
}
