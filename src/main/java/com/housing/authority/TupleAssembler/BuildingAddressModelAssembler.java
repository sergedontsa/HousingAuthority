package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.BuildingAddressController;
import com.housing.authority.Tuples.BuildingAddress;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BuildingAddressModelAssembler implements RepresentationModelAssembler<BuildingAddress, EntityModel<BuildingAddress>> {
    @Override
    public @NotNull EntityModel<BuildingAddress> toModel(@NotNull BuildingAddress entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(BuildingAddressController.class).readOne(entity.getId())).withSelfRel(),
                linkTo(methodOn(BuildingAddressController.class).readAll()).withRel("buildingAddress"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<BuildingAddress>> toCollectionModel(@NotNull Iterable<? extends BuildingAddress> entities) {
        return null;
    }
}
