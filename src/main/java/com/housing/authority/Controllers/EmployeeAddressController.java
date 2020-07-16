package com.housing.authority.Controllers;

import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.EmployeeAddressRepository;
import com.housing.authority.Repository.EmployeeRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.EmployeeAddressModelAssembler;
import com.housing.authority.Tuples.EmployeeAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.EMPLOYEE_ADDRESS_CONTROLLER)
public class EmployeeAddressController {

    @Autowired
    private final EmployeeAddressRepository repository;
    @Autowired
    private final EmployeeAddressModelAssembler assembler;
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeAddressController(EmployeeAddressRepository repository, EmployeeAddressModelAssembler assembler, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.employeeRepository = employeeRepository;
    }

    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_ADDRESS_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<EmployeeAddress>> readAll() {
        List<EntityModel<EmployeeAddress>> entityModels = this.repository
                .findAll()
                .stream()
                .map(this.assembler::toModel).collect(Collectors.toList());

       return new CollectionModel<>(entityModels, linkTo(methodOn(EmployeeAddressController.class)
       .readAll())
       .withSelfRel());

    }

    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_ADDRESS_GET_WITH_EMPLOYEE_ID, produces = Constant.PRODUCE)
    public EntityModel<EmployeeAddress> readOne(@PathVariable String employeeId) {
        if (!this.repository.existsById(employeeId) || !this.employeeRepository.existsById(employeeId)){
            throw new ResourceNotFoundException("Resource id: " + employeeId + " could not be found");
        }

        return this.assembler.toModel(this.repository.getOne(employeeId));
    }
    @CrossOrigin
    @PostMapping(value = Constant.EMPLOYEE_ADDRESS_SAVE_WITH_EMPLOYEE_ID, consumes = Constant.CONSUMES)
    public ResponseEntity<?> create(@PathVariable String employeeId, @RequestBody EmployeeAddress object) {
        if (this.repository.existsById(employeeId) || !this.employeeRepository.existsById(employeeId)){

            throw new ResourceNotFoundException("Resource Id: " + employeeId + " employee does exist or there is an address with the provided id");
        }

        return this.employeeRepository.findById(employeeId).map(employee -> {
            object.setEmployeeId(employeeId);


            EntityModel<EmployeeAddress> entityModel = this.assembler.toModel(this.repository.save(object));
            this.employeeRepository.setEmployeeAddressId(employeeId);

            employeeRepository.setAddressId(employeeId, employeeId);

            return ResponseEntity.created(assembler.toModel(this.repository.save(object))
            .getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
            .body(entityModel);
        }).orElseThrow(()->  new ResourceNotFoundException("Resource ID: " + employeeId+ " could not be found"));

    }
    @CrossOrigin
    @PutMapping(value = Constant.EMPLOYEE_ADDRESS_UPDATE_WITH_EMPLOYEE_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    public ResponseEntity<?> update(@PathVariable int employeeId, @RequestBody EmployeeAddress address) {
        return null;
    }
    @CrossOrigin
    @DeleteMapping(value = Constant.EMPLOYEE_ADDRESS_DELETE_WITH_EMPLOYEE_ID)
    public ResponseEntity<?> delete(@PathVariable String employeeId) {
        if (!repository.existsById(employeeId)){
            throw new ResourceNotFoundException("Resource id: " + employeeId+ " could not be found ");
        }

        try {
            this.employeeRepository.changeEmployeeAddressIdToNull(null, employeeId);
        }catch (ResourceNotFoundException exception){
            exception.printStackTrace();
        }


        return this.repository.findById(employeeId).map(address -> {
            this.repository.delete(address);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Resource id: " + employeeId+ " could not be found "));
    }
}
