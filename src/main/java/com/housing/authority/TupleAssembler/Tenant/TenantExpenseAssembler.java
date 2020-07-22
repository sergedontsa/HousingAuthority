package com.housing.authority.TupleAssembler.Tenant;

import com.housing.authority.Controllers.Tenant.TenantExpenseController;
import com.housing.authority.Tuples.Tenant.TenantExpense;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class TenantExpenseAssembler implements RepresentationModelAssembler<TenantExpense, EntityModel<TenantExpense>> {
    @Override
    public @NotNull EntityModel<TenantExpense> toModel(@NotNull TenantExpense entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(TenantExpenseController.class).readOne(entity.getExpenseId())).withSelfRel(),
                linkTo(methodOn(TenantExpenseController.class).readAll()).withRel("Expense"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<TenantExpense>> toCollectionModel(@NotNull Iterable<? extends TenantExpense> entities) {
        return null;
    }
}
