package com.housing.authority.Controllers.Tenant;

import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.Apartment.ApartmentRepository;
import com.housing.authority.Repository.Building.BuildingRepository;
import com.housing.authority.Repository.Employee.EmployeeRepository;
import com.housing.authority.Repository.Tenant.TenantExpenseRepository;
import com.housing.authority.Repository.Tenant.TenantRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.Tenant.TenantExpenseAssembler;
import com.housing.authority.Tuples.Tenant.TenantExpense;
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
@RequestMapping(value = Constant.TENANT_EXPENSE_CONTROLLER)
public class TenantExpenseController {

    @Autowired
    private final TenantRepository tenantRepository;
    @Autowired
    private final TenantExpenseRepository tenantExpenseRepository;
    @Autowired
    private final TenantExpenseAssembler tenantExpenseAssembler;
    @Autowired
    private final ApartmentRepository apartmentRepository;
    @Autowired
    private final BuildingRepository buildingRepository;
    @Autowired
    private final EmployeeRepository employeeRepository;

    public TenantExpenseController(TenantRepository tenantRepository, TenantExpenseRepository tenantExpenseRepository, TenantExpenseAssembler tenantExpenseAssembler, ApartmentRepository apartmentRepository, BuildingRepository buildingRepository, EmployeeRepository employeeRepository) {
        this.tenantRepository = tenantRepository;
        this.tenantExpenseRepository = tenantExpenseRepository;
        this.tenantExpenseAssembler = tenantExpenseAssembler;
        this.apartmentRepository = apartmentRepository;
        this.buildingRepository = buildingRepository;
        this.employeeRepository = employeeRepository;
    }

    @CrossOrigin
    @GetMapping(value = Constant.TENANT_EXPENSE_GET_ALL, produces = Constant.PRODUCE)
    public CollectionModel<EntityModel<TenantExpense>> readAll(){
        List<EntityModel<TenantExpense>> entityModels = this.tenantExpenseRepository
                .findAll()
                .stream()
                .map(this.tenantExpenseAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(entityModels,
                linkTo(methodOn(TenantExpenseController.class)
                .readAll()).withSelfRel());
    }

    @CrossOrigin
    @GetMapping(value = Constant.TENANT_EXPENSE_GET_WITH_ID, produces = Constant.PRODUCE)
    public EntityModel<TenantExpense> readOne(@PathVariable String expenseId){
        if (!this.tenantRepository.existsById(expenseId)){
            throw new ResourceNotFoundException("Resource Id" + expenseId + " could not be found");
        }else {
            if (this.tenantExpenseRepository.findById(expenseId).isPresent()) {
                return this.tenantExpenseAssembler.toModel(this.tenantExpenseRepository.findById(expenseId).get());
            }else {
                return null;
            }
        }
    }
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = Constant.TENANT_EXPENSE_SAVE, consumes = Constant.CONSUMES)
    public ResponseEntity<?> create(@PathVariable String tenantId, @PathVariable String buildingId, @PathVariable String apartmentId, @RequestBody TenantExpense tenantExpense ){
        if(!this.tenantRepository.existsById(tenantId))
            throw new ResourceNotFoundException("Resource Id" + tenantId + " could not be found");
        if(!buildingRepository.existsById(buildingId))
            throw new ResourceNotFoundException("Resource Id" + buildingId + " could not be found");
        if (!apartmentRepository.existsById(apartmentId))
            throw new ResourceNotFoundException("Resource Id" + apartmentId + " could not be found");
       tenantExpense.setExpenseId(IDGenerator.TENANT_EXPENSE_ID());
       tenantExpense.setTenant(this.tenantRepository.getOne(tenantId));
       tenantExpense.setApartmentId(apartmentId);
       tenantExpense.setBuildingId(buildingId);
       EntityModel<TenantExpense> entityModel = this.tenantExpenseAssembler.toModel(this.tenantExpenseRepository.save(tenantExpense));

        return ResponseEntity.created(this.tenantExpenseAssembler.toModel(this.tenantExpenseRepository.save(tenantExpense))
        .getRequiredLink(IanaLinkRelations.SELF)
        .toUri()).body(entityModel);
    }
    @DeleteMapping(value = Constant.TENANT_EXPENSE_DELETE_WITH_ID, produces = Constant.PRODUCE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable String expenseId, @PathVariable String tenantId, @PathVariable String buildingId, @PathVariable String apartmentId){

        if (!this.tenantRepository.existsById(tenantId))
            throw new ResourceNotFoundException("Resource Id" + tenantId + " could not be found");
        if (!this.buildingRepository.existsById(buildingId))
            throw new ResourceNotFoundException("Resource Id" + buildingId + " could not be found");
        if (!this.apartmentRepository.existsById(apartmentId))
            throw new ResourceNotFoundException("Resource Id" + apartmentId + " could not be found");
        if (!this.tenantExpenseRepository.findById(expenseId).isPresent()) {
            throw new ResourceNotFoundException("Resource Id" + expenseId + " could not be found");
        }else {
            this.tenantExpenseRepository.delete(this.tenantExpenseRepository.findById(expenseId).get());
        }

        return null;
    }
    @PatchMapping(path = Constant.TENANT_EXPENSE_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public ResponseEntity<?> update(@PathVariable String expenseId, @PathVariable String tenantId, @PathVariable String buildingId, @PathVariable String apartmentId, @RequestBody TenantExpense tenantExpense){


        return null;
    }
}
