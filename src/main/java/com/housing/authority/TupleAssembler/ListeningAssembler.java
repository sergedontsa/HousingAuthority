package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.ListeningController;
import com.housing.authority.Tuples.Listening;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ListeningAssembler implements RepresentationModelAssembler<Listening, EntityModel<Listening>> {
    @Override
    public  EntityModel<Listening> toModel( Listening entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ListeningController.class).readOne(entity.getApartmentId())).withSelfRel(),
                linkTo(methodOn(ListeningController.class).readAll()).withRel("Listening")
                );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Listening>> toCollectionModel(@NotNull Iterable<? extends Listening> entities) {
        return null;
    }
}
