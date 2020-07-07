package com.housing.authority.Controllers;

import com.housing.authority.Repository.ApartmentRepository;
import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.ApartmentModelAssembler;
import com.housing.authority.Tuples.Apartment;
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
@RequestMapping(value = Constant.APARTMENT_CONTROLLER)
@RequiredArgsConstructor
public class ApartmentController implements ServiceController<Apartment> {
    private final ApartmentRepository apartmentTupleRepository;
    private final ApartmentModelAssembler apartmentModelAssembler;


    //-------------------------------APARTMENT
    @Override
    @GetMapping(value = Constant.APARTMENT_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Apartment>> readAll(){
        List<EntityModel<Apartment>> apartments = this.apartmentTupleRepository.findAll().stream().map(this.apartmentModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(apartments, linkTo(methodOn(ApartmentController.class).readAll()).withSelfRel());
    }

    @Override
    @GetMapping(value = Constant.APARTMENT_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Apartment> readOne(@PathVariable String id){

        if (this.apartmentTupleRepository.findById(id).isPresent()) {
            return this.apartmentModelAssembler.toModel(this.apartmentTupleRepository.findById(id).get());
        }else {
            return null;
        }
    }

    @Override
    public EntityModel<Apartment> readOne(int id) {
        return null;
    }

    @Override
    @PostMapping(value = Constant.APARTMENT_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<?> create(@RequestBody Apartment apartment){
        apartment.setApartmentID(IDGenerator.APARTMENT_ID());
        apartment.setRegisterdate(Constant.getCurrentDateAsString());
        apartment.setLastupdate(Constant.getCurrentDateAsString());
        apartment.setStatus("Available");
        EntityModel<Apartment> entityModel = this.apartmentModelAssembler
                .toModel(this.apartmentTupleRepository.save(apartment));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                .toUri())
                .body(entityModel);

    }
    @Override
    @DeleteMapping(value = Constant.APARTMENT_DELETE_WITH_ID)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void delete(@PathVariable String id){
        if (this.apartmentTupleRepository.findById(id).isPresent()) {
            this.apartmentTupleRepository.delete(this.apartmentTupleRepository.findById(id).get());
        }

    }
    @Override
    @PatchMapping(path = Constant.APARTMENT_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object update(@PathVariable String id, @RequestBody Apartment apartment){
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
            existingApartment.setLastupdate(Constant.getCurrentDateAsString());

            if (apartment.getStatus() == null){
                existingApartment.setStatus("Available");
            }else {
                existingApartment.setStatus(apartment.getStatus());
            }

            this.apartmentTupleRepository.save(existingApartment);
            return HttpStatus.OK;
        }else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
