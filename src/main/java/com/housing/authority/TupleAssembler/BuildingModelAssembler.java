package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.BuildingController;
import com.housing.authority.Tuples.Building;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class BuildingModelAssembler implements RepresentationModelAssembler<Building, EntityModel<Building>> {
    @Override
    public  EntityModel<Building> toModel( Building building) {
        return new EntityModel<>(building,
                linkTo(methodOn(BuildingController.class).readOne(building.getBuildingId())).withSelfRel(),
                linkTo(methodOn(BuildingController.class).readAll()).withRel("buildings")
                );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Building>> toCollectionModel(@NotNull Iterable<? extends Building> entities) {
        return null;
    }
}
