package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.Billing.BillingController;
import com.housing.authority.Tuples.Billing.Billing;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BillingModelAssembler implements RepresentationModelAssembler<Billing, EntityModel<Billing>> {
    @Override
    public EntityModel<Billing> toModel(Billing entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(BillingController.class).readOne(entity.getBillingid())).withSelfRel(),
                linkTo(methodOn(BillingController.class).readAll()).withRel("billing")
                );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Billing>> toCollectionModel(@NotNull Iterable<? extends Billing> entities) {
        return null;
    }
}
