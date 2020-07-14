package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.ApartmentFeeController;
import com.housing.authority.Tuples.ApartmentFee;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ApartmentFeeAssembler implements RepresentationModelAssembler<ApartmentFee, EntityModel<ApartmentFee>> {
    @Override
    public @NotNull EntityModel<ApartmentFee> toModel(@NotNull ApartmentFee entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ApartmentFeeController.class).readOne(entity.getApartmentId())).withSelfRel(),
                linkTo(methodOn(ApartmentFeeController.class).readAll()).withRel("fees"));
    }

    @Override
    public CollectionModel<EntityModel<ApartmentFee>> toCollectionModel(Iterable<? extends ApartmentFee> entities) {
        return null;
    }
}
