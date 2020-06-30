package com.housing.authority.Controllers;

import com.housing.authority.Repository.EmployeeRepository;
import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.EmployeeModelAssembler;
import com.housing.authority.Tuples.Employees;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(value = Constant.EMPLOYEE_CONTROLLER)
@RequiredArgsConstructor
public class EmployeeController implements ServiceController<Employees> {

    private final EmployeeRepository employeeRepository;
    private final EmployeeModelAssembler employeeModelAssembler;

    @Override
    @GetMapping(value = Constant.EMPLOYEE_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Employees>> readAll() {
        List<EntityModel<Employees>> employees = this.employeeRepository.findAll().stream()
                .map(this.employeeModelAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(employees, linkTo(methodOn(EmployeeController.class).readAll()).withSelfRel());
    }

    @Override
    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<Employees> readOne(@PathVariable String id) {
        if (this.employeeRepository.findById(id).isPresent()){
            return employeeModelAssembler.toModel(this.employeeRepository.findById(id).get());
        }else {
            return null;
        }
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = Constant.EMPLOYEE_SAVE, consumes = Constant.CONSUMES)
    public ResponseEntity<?> create(@RequestBody Employees object) {
        object.setEmployeeId(IDGenerator.EMPLOYEE_ID());
        object.setRegisterDate(Constant.getCurrentDateAsString());
        object.setLastupdate(Constant.getCurrentDateAsString());
        EntityModel<Employees> entityModel = employeeModelAssembler.toModel(this.employeeRepository.save(object));
        return ResponseEntity.created(employeeModelAssembler.toModel(this.employeeRepository.save(object)).getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @Override
    @CrossOrigin
    @PatchMapping(path = Constant.EMPLOYEE_UPDATE_WITH_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    @ResponseStatus(code = HttpStatus.OK)
    public Object update(@PathVariable String id, @RequestBody Employees employees) {
        if (this.employeeRepository.findById(id).isPresent()){
            Employees existingEmployee = this.employeeRepository.findById(id).get();

            existingEmployee.setFirstName(employees.getFirstName());
            existingEmployee.setMiddleName(employees.getMiddleName());
            existingEmployee.setLastName(employees.getLastName());

            EntityModel<Employees> entityModel = this.employeeModelAssembler.toModel(this.employeeRepository.save(existingEmployee));
            return ResponseEntity.created(employeeModelAssembler.toModel(this.employeeRepository.save(existingEmployee)).getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);


        }else {
            return HttpStatus.NOT_FOUND;
        }
    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = Constant.EMPLOYEE_DELETE_WITH_ID)
    @CrossOrigin
    public void delete(@PathVariable String id) {
        if (this.employeeRepository.findById(id).isPresent()){
            this.employeeRepository.delete(this.employeeRepository.findById(id).get());
        }
    }
}
