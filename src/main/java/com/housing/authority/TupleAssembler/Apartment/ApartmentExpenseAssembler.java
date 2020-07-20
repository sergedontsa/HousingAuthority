package com.housing.authority.TupleAssembler.Apartment;

import com.housing.authority.Controllers.Apartment.ApartmentExpenseController;
import com.housing.authority.Tuples.Apartment.ApartmentExpense;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ApartmentExpenseAssembler  implements RepresentationModelAssembler<ApartmentExpense, EntityModel<ApartmentExpense>> {
    @Override
    public @NotNull EntityModel<ApartmentExpense> toModel(@NotNull ApartmentExpense entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ApartmentExpenseController.class).readOne(entity.getExpenseId())).withSelfRel(),
                linkTo(methodOn(ApartmentExpenseController.class).readAll()).withRel("Expense"));
    }

    @Override
    public CollectionModel<EntityModel<ApartmentExpense>> toCollectionModel(Iterable<? extends ApartmentExpense> entities) {
        return null;
    }
}
