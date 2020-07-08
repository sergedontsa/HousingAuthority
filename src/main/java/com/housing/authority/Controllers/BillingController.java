package com.housing.authority.Controllers;

import com.housing.authority.Repository.BillingRepository;
import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.BillingModelAssembler;
import com.housing.authority.Tuples.Billing;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
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
        //return new CollectionModel<>(entityModels, linkTo(methodOn(BillingController.class).readAll()).withSelfRel());
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

    public EntityModel<Billing> readOne(int id) {
        return null;
    }

    @PostMapping(value = Constant.BILLING_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Billing object) {
        object.setBillingid(IDGenerator.RECORD_ID());
        return ResponseEntity.created(this.billingModelAssembler
                .toModel(this.billingRepository.save(object))
                .getRequiredLink(IanaLinkRelations.SELF)
                .toUri())
                .body(object);
    }

    public Object update(String id, Billing billing) {
        return null;
    }

    public void delete(String id) {

    }
}
