package com.housing.authority.TupleAssembler.Tenant;

import com.housing.authority.Controllers.Tenant.TenantSubController;
import com.housing.authority.Tuples.Tenant.TenantSub;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class TenantSubAssembler implements RepresentationModelAssembler<TenantSub, EntityModel<TenantSub>> {
    @Override
    public @NotNull EntityModel<TenantSub> toModel(@NotNull TenantSub entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(TenantSubController.class).readOne(entity.getTenantid())).withSelfRel(),
                linkTo(methodOn(TenantSubController.class).readAll()).withRel("Sub_Tenant"));
    }

    @Override
    public CollectionModel<EntityModel<TenantSub>> toCollectionModel(Iterable<? extends TenantSub> entities) {
        return null;
    }
}
