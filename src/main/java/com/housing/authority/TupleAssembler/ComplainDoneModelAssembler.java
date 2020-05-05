package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.HousingController;
import com.housing.authority.Tuples.Complaindone;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ComplainDoneModelAssembler implements RepresentationModelAssembler<Complaindone, EntityModel<Complaindone>> {
    @Override
    public EntityModel<Complaindone> toModel(Complaindone entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(HousingController.class).readOneComplainDone(entity.getId())).withSelfRel(),
                linkTo(methodOn(HousingController.class).readAllComplainDone()).withRel("Completed"));

    }

    @Override
    public CollectionModel<EntityModel<Complaindone>> toCollectionModel(Iterable<? extends Complaindone> entities) {
        return null;
    }
}
