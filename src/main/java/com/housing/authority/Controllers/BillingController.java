package com.housing.authority.Controllers;

import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.ApartmentRepository;
import com.housing.authority.Repository.BillingRepository;
import com.housing.authority.Repository.BuildingRepository;
import com.housing.authority.Repository.TenantRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.BillingModelAssembler;
import com.housing.authority.Tuples.Billing;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@AllArgsConstructor
@RequestMapping(value = Constant.BILLING_CONTROLLER)
public class BillingController {

    private final BillingRepository billingRepository;
    private final BillingModelAssembler billingModelAssembler;
    private final TenantRepository tenantRepository;
    private final ApartmentRepository apartmentRepository;
    private final BuildingRepository buildingRepository;

    @CrossOrigin
    @GetMapping(value = Constant.BILLING_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<Billing>> readAll() {
        List<EntityModel<Billing>> billings = this.billingRepository.findAll().stream().map(this.billingModelAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(billings, linkTo(methodOn(BillingController.class).readAll()).withSelfRel());
    }

    @CrossOrigin
    @GetMapping(value = Constant.BILLING_BY_TENANT_ID, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<Billing>> readByTenantId(@PathVariable String tenantid){
        List<EntityModel<Billing>> entityModels = this.billingRepository.findByTenantId(tenantid).stream().map(this.billingModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(entityModels, linkTo(methodOn(BillingController.class).readByTenantId(tenantid)).withSelfRel());

    }

    @CrossOrigin
    @GetMapping(value = Constant.BILLING_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Billing> readOne(@PathVariable String id) {
        if (this.billingRepository.findById(id).isPresent()){
            return this.billingModelAssembler.toModel(this.billingRepository.findById(id).get());
        }else {
            return null;
        }
    }



    @PostMapping(value = Constant.BILLING_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public Billing create(
            @PathVariable String tenantId,
            @PathVariable String apartmentId,
            @PathVariable String buildingId,
            @RequestBody Billing object) {
        object.setBillingid(IDGenerator.RECORD_ID());
        if (!tenantRepository.existsById(tenantId) ){
            throw new ResourceNotFoundException("TENANT ID: " + tenantId+ " could not be found");
        }
        if (!apartmentRepository.existsById(apartmentId)){
            throw new ResourceNotFoundException("APARTMENT ID: " + apartmentId+ " could not be found");
        }
        if (!buildingRepository.existsById(buildingId)){
            throw new ResourceNotFoundException("BUILDING ID: " + buildingId+ " could not be found");
        }
        return tenantRepository.findById(tenantId).map(tenant -> {
            object.setTenant(tenantRepository.getOne(tenantId));
            object.setApartment(apartmentRepository.getOne(apartmentId));
            object.setBuilding(buildingRepository.getOne(buildingId));

            return billingRepository.save(object);
        }).orElseThrow(()-> new ResourceNotFoundException("TENANT ID: " + tenantId+ " could not be found"));



    }

    public Object update(String id, Billing billing) {
        return null;
    }

    public void delete(String id) {

    }
}
