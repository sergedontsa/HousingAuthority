package com.housing.authority.Controllers.Employee;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.Employee.EmployeeDetailRepository;
import com.housing.authority.Repository.Employee.EmployeeRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.EmployeeDetailModelAssembler;
import com.housing.authority.Tuples.Employee.EmployeeDetail;
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

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping(value = Constant.EMPLOYEE_DETAIL_CONTROLLER)
public class EmployeeDetailController {

    @Autowired
    private final EmployeeDetailRepository employeeDetailRepository;
    @Autowired
    private final EmployeeDetailModelAssembler employeeDetailModelAssembler;
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeDetailController(EmployeeDetailRepository employeeDetailRepository, EmployeeDetailModelAssembler employeeDetailModelAssembler, EmployeeRepository employeeRepository) {
        this.employeeDetailRepository = employeeDetailRepository;
        this.employeeDetailModelAssembler = employeeDetailModelAssembler;
        this.employeeRepository = employeeRepository;
    }

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
    @GetMapping(value = Constant.EMPLOYEE_DETAIL_GET_WITH_EMPLOYEE_ID, produces = Constant.PRODUCE)
    public EntityModel<EmployeeDetail> readOne(@PathVariable String employeeid) {
        if (employeeDetailRepository.findByEmployeeId(employeeid) == null){
            throw new ResourceNotFoundException("Employee id: " + employeeid+ " could not be found");
        }
        return this.employeeDetailModelAssembler
                .toModel(this.employeeDetailRepository
                        .findByEmployeeId(employeeid));
    }
    @CrossOrigin
    @PostMapping(value = Constant.EMPLOYEE_DETAIL_SAVE, produces = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable String employeeid, @RequestBody EmployeeDetail object) {
        if (!employeeRepository.existsById(employeeid)){
            throw new ResourceNotFoundException("Employee id: " + employeeid+ " could not be found");
        }
        return this.employeeRepository.findById(employeeid).map(employee ->{
            object.setEmployeeid(employeeid);


            EntityModel<EmployeeDetail> entityModel = this.employeeDetailModelAssembler.toModel(this.employeeDetailRepository.save(object));

            employeeRepository.setDetailId(employeeid, employeeid);


            return ResponseEntity.created(this.employeeDetailModelAssembler.toModel(this.employeeDetailRepository.save(object))
            .getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
                    .body(entityModel);


            //return employeeDetailRepository.save(object);
        }).orElseThrow(()-> new ResourceNotFoundException("Employee id: " + employeeid+ " could not be found"));

    }

    @CrossOrigin
    @PatchMapping(path = Constant.EMPLOYEE_DETAIL_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable String employeeid, @RequestBody EmployeeDetail request) {
        if (!this.employeeDetailRepository.existsById(employeeid)){
            throw new ResourceNotFoundException("Resource: " + employeeid + " could not be found");
        }
        request.setEmployeeid(employeeid);
        EntityModel<EmployeeDetail> entityModel = this.employeeDetailModelAssembler
                .toModel(this.employeeDetailRepository.save(request));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
        .toUri()).body(entityModel);
    }
    @CrossOrigin
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.EMPLOYEE_DETAIL_DELETE_WITH_ID)
    public ResponseEntity<?> delete( @PathVariable String employeeid) {
        if (!employeeDetailRepository.findEmployeeDetailByEmployee(employeeid).isPresent()){
            throw new ResourceNotFoundException("Employee id: " + employeeid+ " could not be found");
        }
        return employeeDetailRepository.findEmployeeDetailByEmployee(employeeid)
                .map(detail ->{
                    employeeDetailRepository.delete(detail);
                    return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Employee id: " + employeeid+ " could not be found"));

   }


}
