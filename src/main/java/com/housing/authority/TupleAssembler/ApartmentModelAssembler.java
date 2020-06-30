package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.ApartmentController;
import com.housing.authority.Tuples.Apartment;
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
    public EntityModel<Apartment> toModel(Apartment entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ApartmentController.class).readOne(entity.getApartmentID())).withSelfRel(),
                linkTo(methodOn(ApartmentController.class).readAll()).withRel("apartments"));
    }

    @Override
    public CollectionModel<EntityModel<Apartment>> toCollectionModel(Iterable<? extends Apartment> entities) {
        return null;
    }


}
