package com.housing.authority.Controllers.Complain;

import com.housing.authority.Repository.Complain.ComplainDoneRepository;
import com.housing.authority.Repository.Complain.ComplainRepository;
import com.housing.authority.Repository.Employee.EmployeeRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.ComplainDoneModelAssembler;
import com.housing.authority.Tuples.Complain.Complain;
import com.housing.authority.Tuples.Complain.Complaindone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.COMPLAIN_DONE_CONTROLLER)
public class ComplainDoneController {

    @Autowired
    private final ComplainDoneRepository complainDoneRepository;
    @Autowired
    private final ComplainDoneModelAssembler complainDoneModelAssembler;
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final ComplainRepository complainRepository;

    public ComplainDoneController(ComplainDoneRepository complainDoneRepository, ComplainDoneModelAssembler complainDoneModelAssembler, EmployeeRepository employeeRepository, ComplainRepository complainRepository) {
        this.complainDoneRepository = complainDoneRepository;
        this.complainDoneModelAssembler = complainDoneModelAssembler;
        this.employeeRepository = employeeRepository;
        this.complainRepository = complainRepository;
    }


    @GetMapping(value = Constant.COMPLAIN_DONE_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Complaindone>> readAll() {
        List<EntityModel<Complaindone>> complains = this.complainDoneRepository.findAll().stream().map(this.complainDoneModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(complains, linkTo(methodOn(ComplainDoneController.class).readAll()).withSelfRel());

    }

    @CrossOrigin
    @GetMapping(value = Constant.COMPLAIN_DONE_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Complaindone> readOne(@PathVariable String id) {
        if (this.complainDoneRepository.findById(Integer.parseInt(id)).isPresent()){
            return this.complainDoneModelAssembler.toModel(this.complainDoneRepository.findById(Integer.parseInt(id)).get());
        }else {
            return null;
        }
    }

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
