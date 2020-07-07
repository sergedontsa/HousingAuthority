package com.housing.authority.Controllers;

import com.housing.authority.Repository.ComplainDoneRepository;
import com.housing.authority.Repository.ComplainRepository;
import com.housing.authority.Repository.EmployeeRepository;
import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.ComplainDoneModelAssembler;
import com.housing.authority.Tuples.Complain;
import com.housing.authority.Tuples.Complaindone;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constant.COMPLAIN_DONE_CONTROLLER)
public class ComplainDoneController implements ServiceController<Complaindone> {

    private final ComplainDoneRepository complainDoneRepository;
    private final ComplainDoneModelAssembler complainDoneModelAssembler;
    private final EmployeeRepository employeeRepository;
    private final ComplainRepository complainRepository;


    /**
     * This method return the collection of all entity
     *
     * @return the collection
     */
    @Override
    @GetMapping(value = Constant.COMPLAIN_DONE_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Complaindone>> readAll() {
        List<EntityModel<Complaindone>> complains = this.complainDoneRepository.findAll().stream().map(this.complainDoneModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(complains, linkTo(methodOn(ComplainDoneController.class).readAll()).withSelfRel());

    }

    /**
     * Return the entity with id if found in the  server
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @CrossOrigin
    @GetMapping(value = Constant.COMPLAIN_DONE_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Complaindone> readOne(@PathVariable String id) {
        if (this.complainDoneRepository.findById(Integer.parseInt(id)).isPresent()){
            return this.complainDoneModelAssembler.toModel(this.complainDoneRepository.findById(Integer.parseInt(id)).get());
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
    public EntityModel<Complaindone> readOne(int id) {
        return null;
    }

    /**
     * Create the new entity in server
     *
     * @param complaindone the entity
     * @return the create entity
     */
    @Override
    @PostMapping(value = Constant.COMPLAIN_DONE_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<?> create(Complaindone complaindone) {
        if (this.employeeRepository.findById(complaindone.getEmployeeid()).isPresent() && this.complainRepository.findById(complaindone.getComplainId()).isPresent()){
            complaindone.setConfirmationid(IDGenerator.COMPLAIN_DONE_CONFIRMATION());
            setComplainStatus("DONE", complaindone.getComplainId());
            EntityModel<Complaindone> entityModel = this.complainDoneModelAssembler.toModel(this.complainDoneRepository.save(complaindone));
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

        }else {
            return null;
        }
    }


    /**
     * Update the entity in the server
     *
     * @param id           the id of the entity
     * @param complaindone the updated data
     * @return the updated one
     */
    @Override
    public Object update(String id, Complaindone complaindone) {
        return null;
    }

    /**
     * Delete the value in the database
     *
     * @param id the id of the entity to updated
     */
    @Override
    @DeleteMapping(value = Constant.COMPLAIN_DONE_DELETE_WITH_ID)
    @ResponseStatus(code = HttpStatus.CREATED)
    @CrossOrigin
    public void delete(@PathVariable String id) {
        if (this.complainDoneRepository.findById(Integer.parseInt(id)).isPresent()){
            this.complainDoneRepository.delete(this.complainDoneRepository.findById(Integer.parseInt(id)).get());

        }
    }

    private void setComplainStatus(@Param("status") String status, @Param("complainid") String complainid){
        if (this.complainRepository.findById(complainid).isPresent()){
            Complain complain = this.complainRepository.findById(complainid).get();
            complain.setStatus(status);
            this.complainRepository.save(complain);
        }
    }
}
