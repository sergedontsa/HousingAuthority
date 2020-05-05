package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.HousingController;
import com.housing.authority.Tuples.Tenant;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class TenantModelAssembler implements RepresentationModelAssembler<Tenant, EntityModel<Tenant>> {


    @Override
    public EntityModel<Tenant> toModel(Tenant entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(HousingController.class).readOneTenant(entity.getTenantid())).withSelfRel(),
                linkTo(methodOn(HousingController.class).readAllTenant()).withRel("Tenants"));

    }

    @Override
    public CollectionModel<EntityModel<Tenant>> toCollectionModel(Iterable<? extends Tenant> entities) {
        return null;
    }
}
