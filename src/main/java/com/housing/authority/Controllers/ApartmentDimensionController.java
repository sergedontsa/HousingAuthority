package com.housing.authority.Controllers;

import com.housing.authority.Resources.Constant;
import com.housing.authority.Tuples.ApartmentFee;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constant.APARTMENT_DIMENSION_CONTROLLER)
public class ApartmentDimensionController {
    @GetMapping(value = Constant.APARTMENT_DIMENSION_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<ApartmentFee>> readAll(){
        return null;
    }

    @GetMapping(value = Constant.APARTMENT_DIMENSION_GET_WITH_APARTMENT_ID)
    @CrossOrigin
    public EntityModel<ApartmentFee> readOne(@PathVariable String apartmentId){
        return null;
    }

    @PostMapping(value = Constant.APARTMENT_DIMENSION_SAVE_WITH_APARTMENT_ID, consumes = Constant.CONSUMES)
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable String apartmentId, @RequestBody ApartmentFee apartmentFee){
        return null;
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
