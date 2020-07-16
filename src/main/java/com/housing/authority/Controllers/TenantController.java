package com.housing.authority.Controllers;

import com.housing.authority.Enum.ApartmentStatus;
import com.housing.authority.Exception.ResourceNotFoundException;
import com.housing.authority.Repository.ApartmentRepository;
import com.housing.authority.Repository.BuildingRepository;
import com.housing.authority.Repository.TenantRepository;
import com.housing.authority.Resources.Constant;
import com.housing.authority.Resources.IDGenerator;
import com.housing.authority.TupleAssembler.TenantModelAssembler;
import com.housing.authority.Tuples.Tenant;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(value = Constant.TENANT_CONTROLLER)
public class TenantController {

    @Autowired
    private final TenantRepository tenantRepository;
    @Autowired
    private final TenantModelAssembler tenantModelAssembler;
    @Autowired
    private final BuildingRepository buildingRepository;
    @Autowired
    private final ApartmentRepository apartmentRepository;

    public TenantController(TenantRepository tenantRepository, TenantModelAssembler tenantModelAssembler, BuildingRepository buildingRepository, ApartmentRepository apartmentRepository) {
        this.tenantRepository = tenantRepository;
        this.tenantModelAssembler = tenantModelAssembler;
        this.buildingRepository = buildingRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @GetMapping(value = Constant.TENANT_GET_ALL, produces = Constant.PRODUCE)
    @CrossOrigin
    public CollectionModel<EntityModel<Tenant>> readAll(){
        List<EntityModel<Tenant>> tenants = this.tenantRepository.findAll().stream()
                .map(this.tenantModelAssembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(tenants, linkTo(methodOn(TenantController.class).readAll()).withSelfRel());
    }


    @GetMapping(value = Constant.TENANT_GET_WITH_ID, produces = Constant.PRODUCE)
    @CrossOrigin
    public EntityModel<Tenant> readOne(@PathVariable String id){
        if (this.tenantRepository.findById(id).isPresent()){
            return this.tenantModelAssembler.toModel(this.tenantRepository.findById(id).get());
        }else {
            return null;
        }
    }



    @PostMapping(value = Constant.TENANT_SAVE, consumes = Constant.CONSUMES)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable String buildingId, @PathVariable String apartmentid,@RequestBody Tenant tenant){
        if (!this.buildingRepository.existsById(buildingId)){
            throw new ResourceNotFoundException("BUILDING ID" + buildingId + " could not be found");
        }
        if (!this.apartmentRepository.existsById(apartmentid)){
            throw new ResourceNotFoundException("APARTMENT ID" + apartmentid + " could not be found");
        }


        return this.apartmentRepository.findById(apartmentid).map(apartment -> {
            if (apartment.getStatus().equals(String.valueOf(ApartmentStatus.Occupied))){
                throw new ResourceNotFoundException("APARTMENT ID: "+ apartmentid + " is occupied");
            }
            tenant.setTenantid(IDGenerator.TENANT_ID());
            tenant.setApartment(this.apartmentRepository.getOne(apartmentid));
            tenant.setBuildingid(buildingId);
            apartment.setStatus(String.valueOf(ApartmentStatus.Occupied));
            apartmentRepository.save(apartment);


            EntityModel<Tenant> entityModel = tenantModelAssembler
                    .toModel(this.tenantRepository.save(tenant));
            return ResponseEntity.created(tenantModelAssembler.toModel(this.tenantRepository.save(tenant))
            .getRequiredLink(IanaLinkRelations.SELF)
            .toUri()).body(entityModel);
        }).orElseThrow(()-> new ResourceNotFoundException("APARTMENT ID " + apartmentid+ " could not be found"));

    }

    @DeleteMapping(value = Constant.TENANT_DELETE_WITH_ID)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @CrossOrigin
    public void delete(@PathVariable String id){
        if (this.tenantRepository.findById(id).isPresent()){
            this.tenantRepository.delete(this.tenantRepository.findById(id).get());
        }
    }

    @PatchMapping(path = Constant.TENANT_UPDATE_WITH_ID, consumes = Constant.CONSUMES)
    @ResponseStatus(code = HttpStatus.OK)
    @CrossOrigin
    public Object update(@PathVariable String id, @RequestBody Tenant tenant){
        if (this.tenantRepository.findById(id).isPresent()){

                Tenant existingTenant = this.tenantRepository.findById(id).get();
                existingTenant.setFirstname(tenant.getFirstname());
                existingTenant.setMiddlename(tenant.getMiddlename());
                existingTenant.setLastname(tenant.getLastname());
                existingTenant.setEmail(tenant.getEmail());
                existingTenant.setPhonenumber(tenant.getPhonenumber());
                existingTenant.setProfession(tenant.getProfession());
                existingTenant.setDeposite(tenant.getDeposite());
                this.tenantRepository.save(existingTenant);
                return HttpStatus.OK;

        }else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
