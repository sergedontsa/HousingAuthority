package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.Apartment.ApartmentDimensionController;
import com.housing.authority.Tuples.Apartment.ApartmentDimension;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ApartmentDimensionAssembler implements RepresentationModelAssembler<ApartmentDimension, EntityModel<ApartmentDimension>> {
    @Override
    public @NotNull EntityModel<ApartmentDimension> toModel(@NotNull ApartmentDimension entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ApartmentDimensionController.class).readOne(entity.getApartmentId())).withSelfRel(),
                linkTo(methodOn(ApartmentDimensionController.class).readAll()).withRel("dimension"));
    }

    @Override
    public CollectionModel<EntityModel<ApartmentDimension>> toCollectionModel(Iterable<? extends ApartmentDimension> entities) {
        return null;
    }
}
