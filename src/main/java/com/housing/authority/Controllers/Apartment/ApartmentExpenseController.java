package com.housing.authority.Controllers.Apartment;

import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.ApartmentExpenseRepository;
import com.housing.authority.Repository.ApartmentRepository;
import com.housing.authority.Repository.BuildingRepository;
import com.housing.authority.Repository.EmployeeRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.Apartment.ApartmentExpenseAssembler;
import com.housing.authority.Tuples.Apartment.ApartmentExpense;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = Constant.APARTMENT_EXPENSE_CONTROLLER)
public class ApartmentExpenseController {

    @Autowired
    private final ApartmentRepository apartmentRepository;
    @Autowired
    private final ApartmentExpenseRepository apartmentExpenseRepository;

    @Autowired
    private final ApartmentExpenseAssembler apartmentExpenseAssembler;

    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final BuildingRepository buildingRepository;

    public ApartmentExpenseController(ApartmentRepository apartmentRepository, ApartmentExpenseRepository apartmentExpenseRepository, ApartmentExpenseAssembler apartmentExpenseAssembler, EmployeeRepository employeeRepository, BuildingRepository buildingRepository){
        this.apartmentRepository = apartmentRepository;
        this.apartmentExpenseRepository = apartmentExpenseRepository;
        this.apartmentExpenseAssembler = apartmentExpenseAssembler;
        this.employeeRepository = employeeRepository;
        this.buildingRepository = buildingRepository;
    }

    @CrossOrigin
    @GetMapping(value = Constant.APARTMENT_EXPENSE_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<ApartmentExpense>> readAll(){
        List<EntityModel<ApartmentExpense>> entityModels = this.apartmentExpenseRepository
                .findAll()
                .stream()
                .map(this.apartmentExpenseAssembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(entityModels, linkTo(methodOn(ApartmentExpenseController.class).readAll()).withSelfRel());
    }
    @CrossOrigin
    @GetMapping(value = Constant.APARTMENT_EXPENSE_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<ApartmentExpense> readOne(@PathVariable String expenseId){
        return null;
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = Constant.APARTMENT_EXPENSE_CREATE, consumes = Constant.CONSUMES)
    public ResponseEntity<?> create(@PathVariable String employeeId, @PathVariable String apartmentId, @PathVariable String buildingId, @RequestBody ApartmentExpense apartmentExpense){
        if (!this.employeeRepository.existsById(employeeId))
            throw new ResourceNotFoundException("Resource Id: " + employeeId + " could not be found");
        if (!this.apartmentRepository.existsById(apartmentId))
            throw new ResourceNotFoundException("Resource Id: " + apartmentId + " could not be found");
        if (!this.buildingRepository.existsById(buildingId))
            throw new ResourceNotFoundException("Resource Id: " + buildingId + " could not be found");

        apartmentExpense.setExpenseId(IDGenerator.APARTMENT_EXPENSE_ID());
        apartmentExpense.setApartment(this.apartmentRepository.getOne(apartmentId));
        apartmentExpense.setBuildingId(buildingId);
        apartmentExpense.setEmployeeId(employeeId);

        EntityModel<ApartmentExpense> entityModel = this.apartmentExpenseAssembler.toModel(this.apartmentExpenseRepository.save(apartmentExpense));
        return ResponseEntity.created(this.apartmentExpenseAssembler.toModel(this.apartmentExpenseRepository.save(apartmentExpense))
        .getRequiredLink(IanaLinkRelations.SELF)
        .toUri()).body(entityModel);
    }
    @CrossOrigin
    @DeleteMapping(value = Constant.APARTMENT_EXPENSE_DELETE_WITH_ID)
    public ResponseEntity<?> delete(@PathVariable String employeeId, @PathVariable String expenseId, @PathVariable String apartmentId, @PathVariable String buildingId){
        if (!this.apartmentExpenseRepository.findByIdAndEmployeeIdAndApartmentIdAndBuildingId(expenseId, employeeId, apartmentId, buildingId).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            this.apartmentExpenseRepository.deleteById(expenseId);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
    @CrossOrigin
    @PatchMapping(path = Constant.APARTMENT_EXPENSE_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    public ResponseEntity<?> update(@PathVariable String employeeId, @PathVariable String expenseId, @PathVariable String apartmentId, @PathVariable String buildingId, @RequestBody ApartmentExpense apartmentExpense){
        if (!this.apartmentExpenseRepository.findByIdAndEmployeeIdAndApartmentIdAndBuildingId(expenseId, employeeId, apartmentId, buildingId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            apartmentExpense.setExpenseId(expenseId);
            apartmentExpense.setEmployeeId(employeeId);
            apartmentExpense.setApartment(this.apartmentRepository.getOne(apartmentId));
            apartmentExpense.setBuildingId(buildingId);
            this.apartmentExpenseRepository.save(apartmentExpense);
            return new ResponseEntity<ApartmentExpense>(HttpStatus.OK);
        }
    }
}
