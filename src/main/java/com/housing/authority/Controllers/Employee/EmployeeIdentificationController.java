package com.housing.authority.Controllers.Employee;

import com.housing.authority.Repository.Employee.EmployeeIdentificationRepository;
import com.housing.authority.Repository.Employee.EmployeeRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.Employee.EmployeeIdentificationModelAssembler;
import com.housing.authority.Tuples.Employee.EmployeeIdentification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.EMPLOYEE_IDENTIFICATION_CONTROLLER)
public class EmployeeIdentificationController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeIdentificationController.class);

    @Autowired private final EmployeeIdentificationRepository employeeIdentificationRepository;
    @Autowired private final EmployeeIdentificationModelAssembler employeeIdentificationModelAssembler;
    @Autowired private final EmployeeRepository employeeRepository;

    public EmployeeIdentificationController(EmployeeIdentificationRepository employeeIdentificationRepository, EmployeeIdentificationModelAssembler employeeIdentificationModelAssembler, EmployeeRepository employeeRepository) {
        this.employeeIdentificationRepository = employeeIdentificationRepository;
        this.employeeIdentificationModelAssembler = employeeIdentificationModelAssembler;
        this.employeeRepository = employeeRepository;
    }


    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_IDENTIFICATION_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<EmployeeIdentification>> readAll(){
        logger.info("[READ ALL EMPLOYEE IDENTIFICATION]");
        List<EntityModel<EmployeeIdentification>> entityModelList = this.employeeIdentificationRepository
                .findAll()
                .stream()
                .map(this.employeeIdentificationModelAssembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(entityModelList, linkTo(methodOn(EmployeeIdentificationController.class)
        .readAll()).withSelfRel());
    }

    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_IDENTIFICATION_GET_WITH_EMPLOYEE_ID, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<EmployeeIdentification>> readOne(@PathVariable String employeeId){
        logger.info("[READ ONE EMPLOYEE IDENTIFICATION]");
        if (!this.employeeRepository.findById(employeeId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!this.employeeIdentificationRepository.getEmployeeIdentificationByEmployeeId(employeeId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<EntityModel<EmployeeIdentification>> entityModelList = this.employeeIdentificationRepository
                .getAllByEmployeeId(employeeId)
                .stream()
                .map(this.employeeIdentificationModelAssembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(entityModelList, linkTo(methodOn(EmployeeIdentificationController.class)
        .readOne(employeeId)).withSelfRel());



    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = Constant.EMPLOYEE_IDENTIFICATION_SAVE_WITH_EMPLOYEE_ID, consumes = Constant.CONSUMES)
    public ResponseEntity<?> createOne(@PathVariable String employeeId, @RequestBody EmployeeIdentification employeeIdentification){
        if (!this.employeeRepository.findById(employeeId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        employeeIdentification.setEmployeeId(employeeId);
        EntityModel<EmployeeIdentification> entity = this.employeeIdentificationModelAssembler
                .toModel(this.employeeIdentificationRepository.save(employeeIdentification));
        return ResponseEntity.created(this.employeeIdentificationModelAssembler.toModel(this.employeeIdentificationRepository
        .save(employeeIdentification)).getRequiredLink(IanaLinkRelations.SELF)
        .toUri()).body(entity);

    }

    @CrossOrigin
    @DeleteMapping(Constant.EMPLOYEE_IDENTIFICATION_UPDATE_WITH_EMPLOYEE_ID)
    public Object updateOne(@PathVariable String employeeId, @RequestBody EmployeeIdentification employeeIdentification){
        if (!this.employeeRepository.findById(employeeId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!this.employeeIdentificationRepository.getEmployeeIdentificationByEmployeeId(employeeId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        employeeIdentification.setEmployeeId(employeeId);
        this.employeeIdentificationRepository.save(employeeIdentification);
        return this.employeeIdentificationModelAssembler.toModel(this.employeeIdentificationRepository.getEmployeeIdentificationByEmployeeId(employeeId).get());
    }

    @CrossOrigin
    @DeleteMapping(value = Constant.EMPLOYEE_IDENTIFICATION_DELETE_WITH_EMPLOYEE_ID )
    public ResponseEntity<?> deleteOne(@PathVariable String employeeId) {
        if (!this.employeeIdentificationRepository.getEmployeeIdentificationByEmployeeId(employeeId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {

            this.employeeIdentificationRepository.deleteByEmployeeId(employeeId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }
}
