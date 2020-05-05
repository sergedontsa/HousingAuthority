package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.HousingController;
import com.housing.authority.Tuples.Complain;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ComplainModelAssembler implements RepresentationModelAssembler<Complain, EntityModel<Complain>> {
    @Override
    public EntityModel<Complain> toModel(Complain entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(HousingController.class).readOneComplain(entity.getComplainid())).withSelfRel(),
                linkTo(methodOn(HousingController.class).readAllComplain()).withRel("Complain"));
    }

    @Override
    public CollectionModel<EntityModel<Complain>> toCollectionModel(Iterable<? extends Complain> entities) {
        return null;
    }
}
