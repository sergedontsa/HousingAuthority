package com.housing.authority.Controllers.Employee;

import com.housing.authority.Repository.Employee.EmployeeAddressRepository;
import com.housing.authority.Repository.Employee.EmployeeRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.Employee.EmployeeAddressModelAssembler;
import com.housing.authority.Tuples.Employee.EmployeeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.EMPLOYEE_ADDRESS_CONTROLLER)
public class EmployeeAddressController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeAddressController.class);

    @Autowired private final EmployeeAddressRepository employeeAddressRepository;
    @Autowired private final EmployeeAddressModelAssembler employeeAddressModelAssembler;
    @Autowired private final EmployeeRepository employeeRepository;

    public EmployeeAddressController(EmployeeAddressRepository employeeAddressRepository,
                                     EmployeeAddressModelAssembler employeeAddressModelAssembler,
                                     EmployeeRepository employeeRepository) {
        this.employeeAddressRepository = employeeAddressRepository;
        this.employeeAddressModelAssembler = employeeAddressModelAssembler;
        this.employeeRepository = employeeRepository;
    }

    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_ADDRESS_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<EmployeeAddress>> readAllEmployeeAddress(){
        logger.info("[READ ALL]:[EMPLOYEE ADDRESS] ");
        List<EntityModel<EmployeeAddress>> employeesAddress = this.employeeAddressRepository
                .findAll()
                .stream()
                .map(this.employeeAddressModelAssembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(employeesAddress, linkTo(methodOn(EmployeeAddressController.class)
        .readAllEmployeeAddress()).withSelfRel());
    }

    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_ADDRESS_GET_WITH_EMPLOYEE_ID, produces = Constant.PRODUCE)
    public EntityModel<EmployeeAddress> readOneEmployeeAddress(@PathVariable String employeeId){
        logger.info("[READ ONE]:[EMPLOYEE ADDRESS] " + employeeId);
        if (!this.employeeRepository.findById(employeeId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!this.employeeAddressRepository.getAddressWithEmployeeId(employeeId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return this.employeeAddressModelAssembler.toModel(this.employeeAddressRepository.getAddressWithEmployeeId(employeeId).get());
    }

    @CrossOrigin
    @PostMapping(value = Constant.EMPLOYEE_ADDRESS_SAVE_WITH_EMPLOYEE_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createOneEmployeeAddress(@PathVariable String employeeId, @RequestBody EmployeeAddress employeeAddress){
        if (!this.employeeRepository.findById(employeeId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (this.employeeAddressRepository.getAddressWithEmployeeId(employeeId).isPresent()){
            EmployeeAddress a = this.employeeAddressRepository.getAddressWithEmployeeId(employeeId).get();
            employeeAddress.setAddressId(a.getAddressId());
            employeeAddress.setEmployeeId(a.getEmployeeId());
            EntityModel<EmployeeAddress> entity = this.employeeAddressModelAssembler.toModel(this.employeeAddressRepository.save(employeeAddress));
            return ResponseEntity.created(this.employeeAddressModelAssembler.toModel(this.employeeAddressRepository.save(employeeAddress))
            .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entity);
        }else {
            employeeAddress.setEmployeeId(employeeId);
            EntityModel<EmployeeAddress> entity = this.employeeAddressModelAssembler.toModel(this.employeeAddressRepository.save(employeeAddress));
            return ResponseEntity.created(this.employeeAddressModelAssembler.toModel(this.employeeAddressRepository.save(employeeAddress))
            .getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entity);
        }
    }

    @CrossOrigin
    @DeleteMapping(value = Constant.EMPLOYEE_ADDRESS_DELETE_WITH_EMPLOYEE_ID)
    public ResponseEntity<?> deleteEmployeeAddress(@PathVariable String employeeId){
        if (!this.employeeRepository.findById(employeeId).isPresent()){
            logger.error("[DELETE ADDRESS]:[EMPLOYEE NOT FOUND]: " + employeeId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            this.employeeAddressRepository.deleteByEmployeeId(employeeId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @CrossOrigin
    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping(path = Constant.EMPLOYEE_ADDRESS_UPDATE_WITH_EMPLOYEE_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    public Object updateEmployeeAddress(@PathVariable String employeeId, @RequestBody EmployeeAddress employeeAddress){
        if (!this.employeeAddressRepository.getAddressWithEmployeeId(employeeId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!this.employeeRepository.findById(employeeId).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        EmployeeAddress exist = this.employeeAddressRepository.getAddressWithEmployeeId(employeeId).get();
        employeeAddress.setAddressId(exist.getAddressId());
        employeeAddress.setEmployeeId(employeeId);
        this.employeeAddressRepository.save(employeeAddress);
        return this.employeeAddressModelAssembler.toModel(this.employeeAddressRepository.getAddressWithEmployeeId(employeeId).get());

    }
}
