package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.ScheduleController;
import com.housing.authority.Tuples.Schedule;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ScheduleModelAssembler implements RepresentationModelAssembler<Schedule, EntityModel<Schedule>> {
    @Override
    public @NotNull EntityModel<Schedule> toModel(@NotNull Schedule entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ScheduleController.class).readOne(entity.getScheduleid())).withSelfRel(),
                linkTo(methodOn(ScheduleController.class).readAll()).withRel("schedule"));
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Schedule>> toCollectionModel(@NotNull Iterable<? extends Schedule> entities) {
        return null;
    }
}
