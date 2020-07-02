package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.ApartmentController;
import com.housing.authority.Tuples.Apartment;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;


@Component
public class ApartmentModelAssembler implements RepresentationModelAssembler<Apartment, EntityModel<Apartment>>
{

    @Override
    public @NotNull EntityModel<Apartment> toModel(@NotNull Apartment entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ApartmentController.class).readOne(entity.getApartmentID())).withSelfRel(),
                linkTo(methodOn(ApartmentController.class).readAll()).withRel("apartments"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Apartment>> toCollectionModel(@NotNull Iterable<? extends Apartment> entities) {
        return null;
    }


}
