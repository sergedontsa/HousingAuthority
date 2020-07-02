package com.housing.authority.Controllers;

import com.housing.authority.Repository.ApartmentRepository;
import com.housing.authority.Repository.BuildingRepository;
import com.housing.authority.Repository.ServiceController;
import com.housing.authority.Repository.TenantRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.TenantModelAssembler;
import com.housing.authority.Tuples.Tenant;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(value = Constant.TENANT_CONTROLLER)
@RequiredArgsConstructor
public class TenantController implements ServiceController<Tenant> {

    private final TenantRepository tenantRepository;
    private final TenantModelAssembler tenantModelAssembler;
    private final BuildingRepository buildingRepository;
    private final ApartmentRepository apartmentRepository;

    @Override
    @GetMapping(value = Constant.TENANT_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Tenant>> readAll(){
        List<EntityModel<Tenant>> tenants = this.tenantRepository.findAll().stream()
                .map(this.tenantModelAssembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(tenants, linkTo(methodOn(TenantController.class).readAll()).withSelfRel());
    }

    @Override
    @GetMapping(value = Constant.TENANT_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Tenant> readOne(@PathVariable String id){
        if (this.tenantRepository.findById(id).isPresent()){
            return this.tenantModelAssembler.toModel(this.tenantRepository.findById(id).get());
        }else {
            return null;
        }
    }

    /**
     * In case the entity is referred with the id with type integer
     * Return the entity with id if found in the  server
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public EntityModel<Tenant> readOne(int id) {
        return null;
    }

    @Override
    @PostMapping(value = Constant.TENANT_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Tenant tenant){
        tenant.setTenantid(IDGenerator.TENANT_ID());
        tenant.setRegisterdate(Constant.getCurrentDateAsString());
        tenant.setLastupdate(Constant.getCurrentDateAsString());
        EntityModel<Tenant> entityModel = this.tenantModelAssembler.toModel(this.tenantRepository.save(tenant));
        return ResponseEntity.created(this.tenantModelAssembler.toModel(this.tenantRepository.save(tenant)).getRequiredLink(IanaLinkRelations.SELF)
                .toUri()).body(entityModel);
    }
    @Override
    @DeleteMapping(value = Constant.TENANT_DELETE_WITH_ID)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void delete(@PathVariable String id){
        if (this.tenantRepository.findById(id).isPresent()){
            this.tenantRepository.delete(this.tenantRepository.findById(id).get());
        }
    }
    @Override
    @PatchMapping(path = Constant.TENANT_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object update(@PathVariable String id, @RequestBody Tenant tenant){
        if (this.tenantRepository.findById(id).isPresent()){

            if (!this.buildingRepository.findById(tenant.getBuildingid()).isPresent() || !this.apartmentRepository.findById(tenant.getApartmentid()).isPresent()){
                return HttpStatus.NOT_FOUND;
            }else {
                Tenant existingTenant = this.tenantRepository.findById(id).get();
                existingTenant.setApartmentid(tenant.getApartmentid());
                existingTenant.setBuildingid(tenant.getBuildingid());
                existingTenant.setFirstname(tenant.getFirstname());
                existingTenant.setMiddlename(tenant.getMiddlename());
                existingTenant.setLastname(tenant.getLastname());
                existingTenant.setEmail(tenant.getEmail());
                existingTenant.setPhonenumber(tenant.getPhonenumber());
                existingTenant.setProfession(tenant.getProfession());
                existingTenant.setDeposite(tenant.getDeposite());
                existingTenant.setLastupdate(Constant.getCurrentDateAsString());
                this.tenantRepository.save(existingTenant);
                return HttpStatus.OK;
            }

        }else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
