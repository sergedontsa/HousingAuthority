package com.housing.authority.Controllers;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.EmployeeDetailRepository;
import com.housing.authority.Repository.EmployeeRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.EmployeeDetailModelAssembler;
import com.housing.authority.Tuples.EmployeeDetail;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
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
@AllArgsConstructor
@RequestMapping(value = Constant.EMPLOYEE_DETAIL_CONTROLLER)
public class EmployeeDetailController {

    private final EmployeeDetailRepository employeeDetailRepository;
    private final EmployeeDetailModelAssembler employeeDetailModelAssembler;
    private final EmployeeRepository employeeRepository;


    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_DETAIL_GET_ALL, produces = Constant.PRODUCE)
    @JsonIgnoreProperties
    public CollectionModel<EntityModel<EmployeeDetail>> readAll() {
        List<EntityModel<EmployeeDetail>> entityModels = this.employeeDetailRepository
                .findAll()
                .stream()
                .map(this.employeeDetailModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(entityModels, linkTo(methodOn(EmployeeDetailController.class).readAll()).withSelfRel());
    }


    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_DETAIL_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<EmployeeDetail> readOne(@PathVariable int id) {
        if (!employeeDetailRepository.existsById(id)){
            throw new ResourceNotFoundException("Employee id: " + id+ " could not be found");
        }
        return this.employeeDetailModelAssembler.toModel(this.employeeDetailRepository.findById(id).get());

    }

    @CrossOrigin
    @PostMapping(value = Constant.EMPLOYEE_DETAIL_SAVE, produces = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDetail create(
            @PathVariable String employeeid,
            @RequestBody EmployeeDetail object) {
        if (!employeeRepository.existsById(employeeid)){
            throw new ResourceNotFoundException("Employee id: " + employeeid+ " could not be found");
        }
        return this.employeeRepository.findById(employeeid).map(employee ->{
            object.setEmployee(employee);
            return employeeDetailRepository.save(object);
        }).orElseThrow(()-> new ResourceNotFoundException("Employee id: " + employeeid+ " could not be found"));

    }

    @CrossOrigin
    @PatchMapping(path = Constant.EMPLOYEE_DETAIL_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    public Object update(
            @PathVariable String employeeid,
            @PathVariable String id,
            @RequestBody EmployeeDetail employeeDetail) {
        return null;
    }
    @CrossOrigin
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.EMPLOYEE_DETAIL_DELETE_WITH_ID)
    public void delete(
            @PathVariable String employeeid,
            @PathVariable String id) {

    }


}
