package com.housing.authority.Controllers;

import com.housing.authority.Enum.ApartmentStatus;
import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.ApartmentRepository;
import com.housing.authority.Repository.BuildingRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.ApartmentModelAssembler;
import com.housing.authority.Tuples.Apartment;
import lombok.NoArgsConstructor;
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

/**
 * Note: still check delete request
 * the request work but does not delete the entity
 */

@RestController
@RequestMapping(value = Constant.APARTMENT_CONTROLLER)
@RequiredArgsConstructor
public class ApartmentController{
    @Autowired
    private final ApartmentRepository apartmentTupleRepository;
    @Autowired
    private final ApartmentModelAssembler apartmentModelAssembler;
    @Autowired
    private final BuildingRepository buildingRepository;

    @GetMapping(value = Constant.APARTMENT_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Apartment>> readAll(){
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

        if (!this.buildingRepository.existsById(buildingId)){
            throw new ResourceNotFoundException("BUILDING ID: " + buildingId+ " could not be found");
        }

        return this.buildingRepository.findById(buildingId).map(building -> {
            apartment.setApartmentID(IDGenerator.APARTMENT_ID());
            apartment.setBuilding(building);
            apartment.setStatus(String.valueOf(ApartmentStatus.Available));
            apartment.setBuildingid(buildingId);
            System.out.println(".........");
            System.out.println(apartment);
            EntityModel<Apartment> entityModel = apartmentModelAssembler.toModel(this.apartmentTupleRepository.save(apartment));
            return ResponseEntity.created(apartmentModelAssembler
                    .toModel(this.apartmentTupleRepository.save(apartment))
            .getRequiredLink(IanaLinkRelations.SELF)
            .toUri()).body(entityModel);
        }).orElseThrow(()-> new ResourceNotFoundException("BUILDING ID: " + buildingId+ " could not be found"));


    }

    @DeleteMapping(value = Constant.APARTMENT_DELETE_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable String id){
        if (!apartmentTupleRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.apartmentTupleRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PatchMapping(path = Constant.APARTMENT_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Apartment apartment){
        if (this.apartmentTupleRepository.findById(id).isPresent()){
            Apartment existingApartment = this.apartmentTupleRepository.findById(id).get();
            existingApartment.setNumBedRoom(apartment.getNumBedRoom());
            existingApartment.setBuildingid(apartment.getBuildingid());
            existingApartment.setNumLivingRoom(apartment.getNumLivingRoom());
            existingApartment.setNumBathRoom(apartment.getNumBathRoom());
            existingApartment.setNumKitchen(apartment.getNumKitchen());
            existingApartment.setNumCloset(apartment.getNumCloset());
            existingApartment.setNumWindows(apartment.getNumWindows());
            existingApartment.setWithBath(apartment.isWithBath());
            existingApartment.setWithWaterBoiler(apartment.isWithWaterBoiler());
            if (apartment.getStatus() == null){
                existingApartment.setStatus("Available");
            }else {
                existingApartment.setStatus(apartment.getStatus());
            }

            this.apartmentTupleRepository.save(existingApartment);
            return new ResponseEntity<Apartment>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
