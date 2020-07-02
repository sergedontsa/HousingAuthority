package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.SubscriberController;
import com.housing.authority.Tuples.Subscriber;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class SubscriberModelAssembler implements RepresentationModelAssembler<Subscriber, EntityModel<Subscriber>> {
    @Override
    public @NotNull EntityModel<Subscriber> toModel(@NotNull Subscriber entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(SubscriberController.class).readOne(entity.getId())).withSelfRel(),
                linkTo(methodOn(SubscriberController.class).readAll()).withRel("subscriber"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Subscriber>> toCollectionModel(@NotNull Iterable<? extends Subscriber> entities) {
        return null;
    }
}
