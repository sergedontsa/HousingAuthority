package com.housing.authority.Controllers;

import com.housing.authority.Repository.BillingRepository;
import com.housing.authority.Repository.ServiceController;
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
public class BillingController implements ServiceController<Billing> {

    private final BillingRepository billingRepository;
    private final BillingModelAssembler billingModelAssembler;
    /**
     * This method return the collection of all entity
     *
     * @return the collection
     */
    @Override
    @CrossOrigin
    @GetMapping(value = Constant.BILLING_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<Billing>> readAll() {
        List<EntityModel<Billing>> billings = this.billingRepository.findAll().stream().map(this.billingModelAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(billings, linkTo(methodOn(BillingController.class).readAll()).withSelfRel());
    }

    /**
     * Return the entity with id if found in the  server
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @CrossOrigin
    @GetMapping(value = Constant.BILLING_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Billing> readOne(@PathVariable String id) {
        if (this.billingRepository.findById(id).isPresent()){
            return this.billingModelAssembler.toModel(this.billingRepository.findById(id).get());
        }else {
            return null;
        }
    }

    /**
     * In case the entity is referred with the id with type integer
     * Return the entity with id if found in the  server
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override

    public EntityModel<Billing> readOne(int id) {
        return null;
    }

    /**
     * Create the new entity in server
     *
     * @param object the entity
     * @return the create entity
     */
    @Override
    @PostMapping(value = Constant.BILLING_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Billing object) {
        object.setBillingid(IDGenerator.RECORD_ID());
        object.setRegisterdate(Constant.getCurrentDateAsString());
        object.setLastupdate(Constant.getCurrentDateAsString());
        EntityModel<Billing> entityModel = this.billingModelAssembler.toModel(this.billingRepository.save(object));
        return ResponseEntity.created(this.billingModelAssembler.toModel(this.billingRepository.save(object))
                .getRequiredLink(IanaLinkRelations.SELF)
                .toUri())
                .body(entityModel);
    }

    /**
     * Update the entity in the server
     *
     * @param id      the id of the entity
     * @param billing the updated data
     * @return the updated one
     */
    @Override
    public Object update(String id, Billing billing) {
        return null;
    }

    /**
     * Delete the value in the database
     *
     * @param id the id of the entity to updated
     */
    @Override
    public void delete(String id) {

    }
}
