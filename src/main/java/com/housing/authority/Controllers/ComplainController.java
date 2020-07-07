package com.housing.authority.Controllers;

import com.housing.authority.Repository.ComplainRepository;
import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.ComplainModelAssembler;
import com.housing.authority.Tuples.Complain;
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
@RequestMapping(value = Constant.COMPLAIN_CONTROLLER)
@RequiredArgsConstructor
public class ComplainController implements ServiceController<Complain> {

    private final ComplainRepository complainRepository;
    private final ComplainModelAssembler complainModelAssembler;

    /**
     * This method return the collection of all entity
     *
     * @return the collection
     */
    @Override
    @GetMapping(value = Constant.COMPLAIN_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Complain>> readAll() {
         List<EntityModel<Complain>> complains = this.complainRepository.findAll().stream().map(this.complainModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(complains, linkTo(methodOn(ComplainController.class).readAll()).withSelfRel());
    }

    /**
     * Return the entity with id if found in the  server
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @GetMapping(value = Constant.COMPLAIN_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Complain> readOne(@PathVariable String id) {
        if (this.complainRepository.findById(id).isPresent()){
            return this.complainModelAssembler.toModel(this.complainRepository.findById(id).get());
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
    public EntityModel<Complain> readOne(int id) {
        return null;
    }

    /**
     * Create the new entity in server
     *
     * @param complain the entity
     * @return the create entity
     */
    @Override
    @PostMapping(value = Constant.COMPLAIN_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<?> create(@RequestBody Complain complain) {

        complain.setStatus("Under Review");
        complain.setComplainid(IDGenerator.COMPLAIN_ID());
        EntityModel<Complain> entityModel = this.complainModelAssembler.toModel(this.complainRepository.save(complain));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }

    /**
     * Update the entity in the server
     *
     * @param id       the id of the entity
     * @param complain the updated data
     * @return the updated one
     */
    @Override
    @PatchMapping(path = Constant.COMPLAIN_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object update(@PathVariable String id, @RequestBody Complain complain) {
        if (this.complainRepository.findById(id).isPresent()){
            Complain existingComplain = this.complainRepository.findById(id).get();
            existingComplain.setPersonid(complain.getPersonid());
            existingComplain.setType(complain.getType());
            existingComplain.setTitle(complain.getTitle());
            existingComplain.setBody(complain.getBody());
            existingComplain.setPhonenumber(complain.getPhonenumber());
            existingComplain.setSeverity(complain.getSeverity());
            existingComplain.setAddress(complain.getAddress());
            if (complain.getStatus() != null){
                existingComplain.setStatus(complain.getStatus());
            }else {
                existingComplain.setStatus("Pending");
            }
            existingComplain.setAssignto(complain.getAssignto());


            EntityModel<Complain> entityModel = this.complainModelAssembler.toModel(this.complainRepository.save(existingComplain));
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);



        }else {
            return null;
        }
    }

    /**
     * Delete the value in the database
     *
     * @param id the id of the entity to updated
     */
    @Override
    @DeleteMapping(value = Constant.COMPLAIN_DELETE_WITH_ID)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void delete(@PathVariable String id) {
        if (this.complainRepository.findById(id).isPresent()){
            this.complainRepository.delete(this.complainRepository.findById(id).get());
        }
    }
}

