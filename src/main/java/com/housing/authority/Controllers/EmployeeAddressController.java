package com.housing.authority.Controllers;

import com.housing.authority.Repository.EmployeeAddressRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.TupleAssembler.EmployeeAddressModelAssembler;
import com.housing.authority.Tuples.BuildingAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = Constant.EMPLOYEE_ADDRESS_CONTROLLER)
@RequiredArgsConstructor
public class EmployeeAddressController {

    private final EmployeeAddressRepository employeeAddressRepository;
    private final EmployeeAddressModelAssembler employeeAddressModelAssembler;

    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_ADDRESS_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<BuildingAddress>> readAll() {
       return null;
    }

    @CrossOrigin
    @GetMapping(value = Constant.EMPLOYEE_ADDRESS_GET_WITH_EMPLOYEE_ID, produces = Constant.PRODUCE)
    public EntityModel<BuildingAddress> readOne(@PathVariable String employeeId) {

        return null;
    }
    @CrossOrigin
    @PostMapping(value = Constant.EMPLOYEE_ADDRESS_SAVE_WITH_EMPLOYEE_ID, consumes = Constant.CONSUMES)
    public ResponseEntity<?> create(@PathVariable String employeeId, @RequestBody BuildingAddress object) {

        return null;
    }
    @CrossOrigin
    @PutMapping(value = Constant.EMPLOYEE_ADDRESS_UPDATE_WITH_EMPLOYEE_ID, consumes = Constant.CONSUMES, produces = Constant.PRODUCE)
    public Object update(@PathVariable int employeeId, @RequestBody BuildingAddress address) {
        return null;
    }
    @CrossOrigin
    @DeleteMapping(value = Constant.EMPLOYEE_ADDRESS_DELETE_WITH_EMPLOYEE_ID)
    public void delete(@PathVariable String employeeId) {

    }
}
