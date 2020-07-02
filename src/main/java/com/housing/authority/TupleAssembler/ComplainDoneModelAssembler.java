package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.ComplainDoneController;
import com.housing.authority.Controllers.HousingController;
import com.housing.authority.Tuples.Complaindone;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ComplainDoneModelAssembler implements RepresentationModelAssembler<Complaindone, EntityModel<Complaindone>> {
    @Override
    public @NotNull EntityModel<Complaindone> toModel(@NotNull Complaindone entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ComplainDoneController.class).readOne(entity.getId())).withSelfRel(),
                linkTo(methodOn(ComplainDoneController.class).readAll()).withRel("Completed"));

    }

    @Override
    public @NotNull CollectionModel<EntityModel<Complaindone>> toCollectionModel(@NotNull Iterable<? extends Complaindone> entities) {
        return null;
    }
}
