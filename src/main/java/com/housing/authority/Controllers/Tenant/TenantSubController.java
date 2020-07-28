package com.housing.authority.Controllers.Tenant;
import com.housing.authority.Enum.TenantStatus;
import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.Apartment.ApartmentRepository;
import com.housing.authority.Repository.Building.BuildingRepository;
import com.housing.authority.Repository.Tenant.TenantRepository;
import com.housing.authority.Repository.Tenant.TenantSubRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.Tenant.TenantSubAssembler;
import com.housing.authority.Tuples.Tenant.TenantSub;
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
@RequestMapping(value = Constant.TENANT_SUB_CONTROLLER)
public class TenantSubController {

    @Autowired
    private final TenantSubAssembler tenantSubAssembler;
    @Autowired
    private final TenantSubRepository tenantSubRepository;
    @Autowired
    private final TenantRepository tenantRepository;
    @Autowired
    private final BuildingRepository buildingRepository;
    @Autowired
    private final ApartmentRepository apartmentRepository;

    public TenantSubController(TenantSubAssembler tenantSubAssembler, TenantSubRepository tenantSubRepository, TenantRepository tenantRepository, BuildingRepository buildingRepository, ApartmentRepository apartmentRepository) {
        this.tenantRepository = tenantRepository;
        this.tenantSubAssembler = tenantSubAssembler;
        this.tenantSubRepository = tenantSubRepository;
        this.buildingRepository = buildingRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @GetMapping(value = Constant.TENANT_SUB_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<TenantSub>> readAll(){
        List<EntityModel<TenantSub>> entityModels = this.tenantSubRepository.findAll().stream().map(this.tenantSubAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(entityModels, linkTo(methodOn(TenantSubController.class).readAll()).withSelfRel());
    }


    @GetMapping(value = Constant.TENANT_SUB_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<TenantSub> readOne(@PathVariable String tenantId){
        if (!this.tenantSubRepository.existsById(tenantId))
            throw new ResourceNotFoundException("Resource Id " + tenantId + " could not be found");

       return this.tenantSubAssembler.toModel(this.tenantSubRepository.getOne(tenantId));
    }
    @PostMapping(value = Constant.TENANT_SUB_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable String buildingId, @PathVariable String apartmentId, @PathVariable String parentTenantId, @RequestBody TenantSub tenantSub){
        if (!this.buildingRepository.existsById(buildingId))
            throw new ResourceNotFoundException("Resource Id " + buildingId + " could not be found");
        if (!this.apartmentRepository.existsById(apartmentId))
            throw new ResourceNotFoundException("Resource Id " + apartmentId + " could not be found");
        if (!this.tenantRepository.existsById(parentTenantId))
            throw new ResourceNotFoundException("Resource Id: " + parentTenantId + " could not be found");
        tenantSub.setTenantid(IDGenerator.TENANT__SUB_ID());
        tenantSub.setStatus(String.valueOf(TenantStatus.Pending));
        tenantSub.setBuildingid(buildingId);
        tenantSub.setTenant(this.tenantRepository.getOne(parentTenantId));

        this.tenantSubRepository.save(tenantSub);

        EntityModel<TenantSub> entityModel = tenantSubAssembler.toModel(this.tenantSubRepository.save(tenantSub));

       return ResponseEntity.created(tenantSubAssembler.toModel(this.tenantSubRepository.save(tenantSub))
       .getRequiredLink(IanaLinkRelations.SELF)
       .toUri()).body(entityModel);



    }

    @DeleteMapping(value = Constant.TENANT_SUB_DELETE_WITH_ID)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void delete(@PathVariable String tenantId, @PathVariable String parentTenantId){

    }

    @PatchMapping(path = Constant.TENANT_SUB_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object update(@PathVariable String tenantId, @PathVariable String parentTenantId, @RequestBody TenantSub tenantSub){
       return null;
    }
    @PatchMapping(path = Constant.TENANT_SUB_ACTIVATE, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object updateTenantStatus(@PathVariable String tenantId, @PathVariable String apartmentId, @PathVariable String buildingId){
        return null;

    }
}
