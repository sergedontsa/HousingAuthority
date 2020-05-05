package com.housing.authority.TupleAssembler;

import com.housing.authority.Controllers.PersonInformationController;
import com.housing.authority.Tuples.Personinformation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PersonInformationModelAssembler implements RepresentationModelAssembler<Personinformation, EntityModel<Personinformation>> {
    @Override
    public EntityModel<Personinformation> toModel(Personinformation personinformation) {
        return new EntityModel<>(personinformation,
                linkTo(methodOn(PersonInformationController.class).readOneRecord(personinformation.getDataid())).withSelfRel(),
                linkTo(methodOn(PersonInformationController.class).readAllRecord()).withRel("Record"));
    }

    @Override
    public CollectionModel<EntityModel<Personinformation>> toCollectionModel(Iterable<? extends Personinformation> entities) {
        return null;
    }
}
