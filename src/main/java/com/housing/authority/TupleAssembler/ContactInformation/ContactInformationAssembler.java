package com.housing.authority.TupleAssembler.ContactInformation;

import com.housing.authority.Controllers.ContactInformaiton.ContactInformationController;
import com.housing.authority.Tuples.ContactInformation.ContactInformation;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ContactInformationAssembler implements RepresentationModelAssembler<ContactInformation, EntityModel<ContactInformation>> {
    @Override
    public @NotNull EntityModel<ContactInformation> toModel(@NotNull ContactInformation entity) {
        return new EntityModel<>(entity,
                linkTo(methodOn(ContactInformationController.class).readOne(entity.getPhonenumber())).withSelfRel(),
                linkTo(methodOn(ContactInformationController.class).readAll()).withRel("Contact"));
    }

    @Override
    public CollectionModel<EntityModel<ContactInformation>> toCollectionModel(Iterable<? extends ContactInformation> entities) {
        return null;
    }
}
